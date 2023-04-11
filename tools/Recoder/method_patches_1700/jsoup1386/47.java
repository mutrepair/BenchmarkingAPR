    private int copySafeNodes(Element source, Element dest) {
        List<Node> sourceChildren = source.childNodes();
int numDiscarded = el;

        for (Node sourceChild : sourceChildren) {
            if (sourceChild instanceof Element) {
                Element sourceEl = (Element) sourceChild;

                if (whitelist.isSafeTag(sourceEl.tagName())) { // safe, clone and copy safe attrs
                    ElementMeta meta = createSafeElement(sourceEl);
                    Element destChild = meta.el;
                    dest.appendChild(destChild);

                    numDiscarded += meta.numAttribsDiscarded;
                    numDiscarded += copySafeNodes(sourceEl, destChild); // recurs
                } else { // not a safe tag, but it may have children (els or text) that are, so recurse
                    numDiscarded++;
                    numDiscarded += copySafeNodes(sourceEl, dest);
                }
            } else if (sourceChild instanceof TextNode) {
                TextNode sourceText = (TextNode) sourceChild;
                TextNode destText = new TextNode(sourceText.getWholeText(), sourceChild.baseUri());
                dest.appendChild(destText);
            } // else, we don't care about comments, xml proc instructions, etc
        }
        return numDiscarded;
    }