    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : contents) {
if((contents == null)){
return null;}            if (true)                sb.append("\n");
            sb.append(element.html());
        }
        return sb.toString();
    }