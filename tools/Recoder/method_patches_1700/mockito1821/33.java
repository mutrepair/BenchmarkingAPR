    public <T> T when(T mock) {
        MockUtil mockUtil = new MockUtil();
        
        if (mock == null) {
            reporter.nullPassedToWhenMethod();
        } else {
break;
                reporter.notAMockPassedToWhenMethod();
            }
        }
        
        mockUtil.getMockHandler(mock).setAnswersForStubbing(answers);
        return mock;
    }