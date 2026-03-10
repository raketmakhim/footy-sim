package repository;

import objects.Team;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

    private static final String CLASSPATH_RESOURCE = "/teams.json";

    public List<Team> loadTeams() {
        try (InputStream is = TeamRepository.class.getResourceAsStream(CLASSPATH_RESOURCE)) {
            if (is == null) {
                throw new RuntimeException("teams.json not found on classpath");
            }
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return parseTeams(json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read teams.json", e);
        }
    }

    public void saveTeams(List<Team> teams, String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"teams\": [\n");
        for (int i = 0; i < teams.size(); i++) {
            Team t = teams.get(i);
            sb.append("    { \"name\": \"").append(t.getTeamName()).append("\"")
              .append(", \"power\": ").append(t.getPower())
              .append(", \"offensivePower\": ").append(t.getOffensivePower())
              .append(", \"defensivePower\": ").append(t.getDefensivePower())
              .append(" }");
            if (i < teams.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]\n}");
        try (Writer w = new FileWriter(filePath, StandardCharsets.UTF_8)) {
            w.write(sb.toString());
        }
    }

    private List<Team> parseTeams(String json) {
        List<Team> teams = new ArrayList<>();
        int arrayStart = json.indexOf('[');
        int arrayEnd   = json.lastIndexOf(']');
        if (arrayStart < 0 || arrayEnd < 0) {
            throw new RuntimeException("Malformed JSON: no teams array found");
        }
        String arrayContent = json.substring(arrayStart + 1, arrayEnd);

        String[] rawObjects = arrayContent.split("\\}");
        for (String raw : rawObjects) {
            raw = raw.replaceAll("^[\\s,\\{]+", "").trim();
            if (raw.isEmpty()) continue;

            String name         = extractString(raw, "name");
            byte power          = extractByte(raw, "power");
            byte offensivePower = extractByte(raw, "offensivePower");
            byte defensivePower = extractByte(raw, "defensivePower");

            if (name != null && !name.isEmpty()) {
                teams.add(new Team(name, power, offensivePower, defensivePower));
            }
        }
        return teams;
    }

    private String extractString(String obj, String key) {
        String search = "\"" + key + "\"";
        int keyIdx = obj.indexOf(search);
        if (keyIdx < 0) return null;
        int colonIdx   = obj.indexOf(':', keyIdx);
        int quoteOpen  = obj.indexOf('"', colonIdx);
        int quoteClose = obj.indexOf('"', quoteOpen + 1);
        if (quoteOpen < 0 || quoteClose < 0) return null;
        return obj.substring(quoteOpen + 1, quoteClose);
    }

    private byte extractByte(String obj, String key) {
        String search = "\"" + key + "\"";
        int keyIdx = obj.indexOf(search);
        if (keyIdx < 0) return 0;
        int colonIdx = obj.indexOf(':', keyIdx);
        int start = colonIdx + 1;
        while (start < obj.length() && !Character.isDigit(obj.charAt(start))) start++;
        int end = start;
        while (end < obj.length() && Character.isDigit(obj.charAt(end))) end++;
        if (start == end) return 0;
        return (byte) Integer.parseInt(obj.substring(start, end));
    }
}
