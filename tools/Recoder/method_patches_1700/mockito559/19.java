    public void addAnswer(Answer answer) {
        registeredInvocations.removeLast();
super.addAnswer();
        addAnswer(answer, true);    }