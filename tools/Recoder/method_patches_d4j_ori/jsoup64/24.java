    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
tb.transition(Text);
        tb.insert(startTag);        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }