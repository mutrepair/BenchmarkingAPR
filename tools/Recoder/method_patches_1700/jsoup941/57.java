    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
if(((sb.length() < 0) && (sb.length() == null))){
                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }