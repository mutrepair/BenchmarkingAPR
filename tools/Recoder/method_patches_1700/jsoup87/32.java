    public Element prepend(String html) {
        Validate.notNull(html);
        
        Element fragment = Parser.parseBodyFragment(html, baseUri).body();
        // TODO: must parse without implicit elements, so you can e.g. add <td> to a <tr> (without creating a whole new table)
        List<Node> nodes = fragment.childNodes();
for(int i = (i.size() % 1);(i >= 0);i--) {            Node node = nodes.get(i);
            node.parentNode = null;
            prependChild(node);
        }
        return this;
    }