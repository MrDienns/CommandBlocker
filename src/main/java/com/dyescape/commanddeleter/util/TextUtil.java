package com.dyescape.commanddeleter.util;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class TextUtil {

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    private static final Map<String, String> parseReplacements;
    private static final Pattern parsePattern;

    // -------------------------------------------- //
    // STATIC
    // -------------------------------------------- //

    static
    {
        // Create the parse replacements map
        parseReplacements = new HashMap<>();

        // Color by name
        parseReplacements.put("<empty>", "");
        parseReplacements.put("<black>", "\u00A70");
        parseReplacements.put("<navy>", "\u00A71");
        parseReplacements.put("<green>", "\u00A72");
        parseReplacements.put("<teal>", "\u00A73");
        parseReplacements.put("<red>", "\u00A74");
        parseReplacements.put("<purple>", "\u00A75");
        parseReplacements.put("<gold>", "\u00A76");
        parseReplacements.put("<orange>", "\u00A76");
        parseReplacements.put("<silver>", "\u00A77");
        parseReplacements.put("<gray>", "\u00A78");
        parseReplacements.put("<grey>", "\u00A78");
        parseReplacements.put("<blue>", "\u00A79");
        parseReplacements.put("<lime>", "\u00A7a");
        parseReplacements.put("<aqua>", "\u00A7b");
        parseReplacements.put("<rose>", "\u00A7c");
        parseReplacements.put("<pink>", "\u00A7d");
        parseReplacements.put("<yellow>", "\u00A7e");
        parseReplacements.put("<white>", "\u00A7f");
        parseReplacements.put("<magic>", "\u00A7k");
        parseReplacements.put("<bold>", "\u00A7l");
        parseReplacements.put("<strong>", "\u00A7l");
        parseReplacements.put("<strike>", "\u00A7m");
        parseReplacements.put("<strikethrough>", "\u00A7m");
        parseReplacements.put("<under>", "\u00A7n");
        parseReplacements.put("<underline>", "\u00A7n");
        parseReplacements.put("<italic>", "\u00A7o");
        parseReplacements.put("<em>", "\u00A7o");
        parseReplacements.put("<reset>", "\u00A7r");

        // Color by semantic functionality
        parseReplacements.put("<l>", "\u00A72");
        parseReplacements.put("<logo>", "\u00A72");
        parseReplacements.put("<a>", "\u00A76");
        parseReplacements.put("<art>", "\u00A76");
        parseReplacements.put("<n>", "\u00A77");
        parseReplacements.put("<notice>", "\u00A77");
        parseReplacements.put("<i>", "\u00A7e");
        parseReplacements.put("<info>", "\u00A7e");
        parseReplacements.put("<g>", "\u00A7a");
        parseReplacements.put("<good>", "\u00A7a");
        parseReplacements.put("<b>", "\u00A7c");
        parseReplacements.put("<bad>", "\u00A7c");

        parseReplacements.put("<k>", "\u00A7b");
        parseReplacements.put("<key>", "\u00A7b");

        parseReplacements.put("<v>", "\u00A7d");
        parseReplacements.put("<value>", "\u00A7d");
        parseReplacements.put("<h>", "\u00A7d");
        parseReplacements.put("<highlight>", "\u00A7d");

        parseReplacements.put("<c>", "\u00A7b");
        parseReplacements.put("<command>", "\u00A7b");
        parseReplacements.put("<p>", "\u00A73");
        parseReplacements.put("<parameter>", "\u00A73");
        parseReplacements.put("&&", "&");
        parseReplacements.put("§§", "§");

        // Color by number/char
        for (int i = 48; i <= 122; i++)
        {
            char c = (char)i;
            parseReplacements.put("§"+c, "\u00A7"+c);
            parseReplacements.put("&"+c, "\u00A7"+c);
            if (i == 57) i = 96;
        }

        // Build the parse pattern and compile it
        StringBuilder patternStringBuilder = new StringBuilder();
        for (String find : parseReplacements.keySet())
        {
            patternStringBuilder.append('(');
            patternStringBuilder.append(Pattern.quote(find));
            patternStringBuilder.append(")|");
        }
        String patternString = patternStringBuilder.toString();
        patternString = patternString.substring(0, patternString.length()-1); // Remove the last |
        parsePattern = Pattern.compile(patternString);
    }

    // -------------------------------------------- //
    // LOGIC
    // -------------------------------------------- //

    public String parse(String string) {
        if (string == null) return null;
        StringBuffer ret = new StringBuffer();
        Matcher matcher = parsePattern.matcher(string);
        while (matcher.find())
        {
            matcher.appendReplacement(ret, parseReplacements.get(matcher.group(0)));
        }
        matcher.appendTail(ret);
        return ret.toString();
    }

}
