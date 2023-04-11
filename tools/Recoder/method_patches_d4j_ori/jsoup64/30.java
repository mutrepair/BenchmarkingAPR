    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
super.handleRawtext();
        tb.insert(startTag);        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }