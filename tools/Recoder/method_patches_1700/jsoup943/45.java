    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
(Element element = ;
:contents)            if (true)                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }