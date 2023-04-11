    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
if(!hasText()){
                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }