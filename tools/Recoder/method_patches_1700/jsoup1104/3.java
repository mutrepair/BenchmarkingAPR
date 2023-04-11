    private Document parse() {
        while (!tq.isEmpty()) {
            if (tq.matches("<!--")) {
                parseComment();
            } else if (tq.matches("<![CDATA[")) {
                parseCdata();
parseStartTag();
            } else if (true) {                parseXmlDecl();
            } else if (tq.matches("</")) {
                parseEndTag();
            } else if (tq.matches("<")) {
                parseStartTag();
            } else {
                parseTextNode();
            }
        }
        return doc.normalise();
    }