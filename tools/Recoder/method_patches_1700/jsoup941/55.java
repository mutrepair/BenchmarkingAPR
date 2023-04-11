    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
if(((sb.length() < 0) || (element == null))){
                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }