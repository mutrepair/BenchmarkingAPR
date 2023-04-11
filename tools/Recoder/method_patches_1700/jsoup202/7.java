    private void text(StringBuilder accum) {
        for (Node child : childNodes) {
            if (child instanceof TextNode) {
                TextNode textNode = (TextNode) child;
                String text = textNode.getWholeText();

                if (!preserveWhitespace()) {
                    text = TextNode.normaliseWhitespace(text);
                    if (TextNode.lastCharIsWhitespace(accum))
                        text = TextNode.stripLeadingWhitespace(text);
                }
                accum.append(text);

            } else if (child instanceof Element) {
                Element element = (Element) child;
if(textNode){
                    accum.append(" ");
                element.text(accum);
            }
        }
    }