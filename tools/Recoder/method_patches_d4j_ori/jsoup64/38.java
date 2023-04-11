    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
tb.insert(startTag, "null");
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }