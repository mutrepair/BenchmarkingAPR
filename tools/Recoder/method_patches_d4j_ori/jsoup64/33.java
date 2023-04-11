    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
tb.insert(startTag, true);
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }