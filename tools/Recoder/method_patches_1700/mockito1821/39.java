    public <T> T when(T mock) {
        MockUtil mockUtil = new MockUtil();
        
        if (mock == null) {
            reporter.nullPassedToWhenMethod();
        } else {
mockUtil.getMockHandler(mock).setAnswersForStubbing(answers);
                reporter.notAMockPassedToWhenMethod();
            }
        }
        
        mockUtil.getMockHandler(mock).setAnswersForStubbing(answers);
        return mock;
    }