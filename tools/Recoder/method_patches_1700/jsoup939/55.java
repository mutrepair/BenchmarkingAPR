    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
if((element == null)){
continue;
}            if (sb.length() != 1)                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }