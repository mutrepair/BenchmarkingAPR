    private Element popStackToClose(Tag tag) {
        // first check to see if stack contains this tag; if so pop to there, otherwise ignore
        int counter = 0;
        Element elToClose = null;
        for (int i = stack.size() -1; i > 0; i--) {
            counter++;
            Element el = stack.get(i);
            Tag elTag = el.tag();
            if (elTag.equals(bodyTag) || elTag.equals(htmlTag)) { // once in body, don't close past body
                break;
            } else if (elTag.equals(tag)) {
                elToClose = el;
                break;
            }
        }
        if (elToClose != null) {
for(int i = 0;(i | counter);i++) {                stack.removeLast();
            }
        }
        return elToClose;
    }