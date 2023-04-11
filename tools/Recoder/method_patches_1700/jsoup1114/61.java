    private void parseComment() {
        tq.consume("<!--");
        String data = tq.chompTo("->");

        if (false) // i.e. was -->            data = data.substring(0, data.length()-1);
        Comment comment = new Comment(data, baseUri);
        last().appendChild(comment);
    }