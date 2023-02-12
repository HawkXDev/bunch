import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use principles of regular expressions and Stack type to find for all tag lines in the input string
 */
public class ExtractTags {

    public static void main(String[] args) {
        String inputStr = "Info about Leela <span xml:lang=\"en\" lang=\"en\"><b><span>Turanga Leela\n" +
                "</span></b></span><span>Super</span><span>girl</span>";
        String tagInput = "span";

        inputStr = inputStr.replaceAll("\n", "");

        Tag tagOpen, tagEnd;
        FinderTags finderTags = new FinderTags(inputStr, tagInput);

        do {
            Stack<Tag> stack = new Stack<>();
            tagOpen = finderTags.findNext();

            if (tagOpen != null) {

                if (tagOpen.type == TagType.OPEN) {
                    stack.push(tagOpen);
                    int indNext = tagOpen.end;

                    do {

                        tagEnd = finderTags.findNext(indNext);
                        if (tagEnd == null)
                            throw new RuntimeException("Incorrect tag order in the input string");

                        indNext = tagEnd.end;

                        if (tagEnd.type == TagType.OPEN) {
                            stack.add(tagEnd);
                        } else {
                            stack.pop();
                        }

                    } while (stack.size() > 0);

                    String tagFinal = inputStr.substring(tagOpen.start, tagEnd.end);
                    System.out.println(tagFinal);
                }

                finderTags.setCursor(tagOpen.end);
            }

        } while (tagOpen != null);
    }

    private enum TagType {
        OPEN, CLOSE
    }

    private static class Tag {
        public TagType type;
        public int start;
        public int end;

        public Tag(TagType type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type + " start: " + start + " end: " + end;
        }
    }

    private static class FinderTags {
        private final Pattern patternOpenTag;
        private final Pattern patternCloseTag;
        private final String str;
        private int cursor;

        public FinderTags(String str, String tag) {
            patternOpenTag = Pattern.compile("<" + tag + "(.*?)>");
            patternCloseTag = Pattern.compile("</" + tag + ">");
            this.str = str;
        }

        public Tag findNext() {
            return findNext(cursor);
        }

        public Tag findNext(int start) {
            Tag openTag = new Tag(TagType.OPEN);
            Tag closeTag = new Tag(TagType.CLOSE);
            boolean anyTagsFound = false;

            Matcher matcherOpen = patternOpenTag.matcher(str);
            if (matcherOpen.find(start)) {
                openTag.start = matcherOpen.start();
                openTag.end = matcherOpen.end();
                anyTagsFound = true;
            }

            Matcher matcherClose = patternCloseTag.matcher(str);
            if (matcherClose.find(start)) {
                closeTag.start = matcherClose.start();
                closeTag.end = matcherClose.end();
                anyTagsFound = true;
            }

            if (!anyTagsFound) return null;

            if (openTag.start < closeTag.start && openTag.end != 0) {
                return openTag;
            } else {
                return closeTag;
            }
        }

        public void setCursor(int newCursor) {
            cursor = newCursor;
        }
    }

}
