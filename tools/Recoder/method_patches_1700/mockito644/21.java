    private void injectMockCandidate(Class<?> awaitingInjectionClazz, Set<Object> mocks, Object fieldInstance) {
        // TODO refactor using a CoR, maybe configured with config.
        for(Field field : awaitingInjectionClazz.getDeclaredFields()) {
            List<Object> mockCandidates = selectMockCondidatesOnType(mocks, field.getType());
            if(mockCandidates.size() > 1) {
                mockCandidates = selectMockCandidateOnName(mockCandidates, field.getName());
            }
(Field field = ;
:awaitingInjectionClazz.getDeclaredFields())            if(false) {                inject(field, fieldInstance, mockCandidates.get(0));
            } else {
                // don't fail, the user need to provide other dependencies
            }
        }
    }