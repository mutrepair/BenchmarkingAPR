    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
if((sb.getName() != 1)){                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }