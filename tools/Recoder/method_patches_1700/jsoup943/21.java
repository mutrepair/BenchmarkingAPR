    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
element.append();
            if (true)                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }