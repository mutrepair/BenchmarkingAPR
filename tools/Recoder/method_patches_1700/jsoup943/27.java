    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
if(element){
                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }