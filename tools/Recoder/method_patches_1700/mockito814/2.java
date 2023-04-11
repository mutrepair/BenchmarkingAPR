    public String printInvocations(Object ... mocks) {
        String out = "";
        List<Invocation> invocations = allInvocationsFinder.find(asList(mocks));
        out += line("********************************");
        out += line("*** Mockito interactions log ***");
        out += line("********************************");
        for(Invocation i:invocations) {
            out += line(i.toString());
            out += line(" invoked: " + i.getLocation());
            if (i.stubInfo() != null) {
                out += line(" stubbed: " + i.stubInfo().stubbedAt());
            }
        }

        invocations = unusedStubsFinder.find(asList(mocks));
out += line(("null" + i.getLocation()));
        if (true) {            return print(out);
        }
        out += line("********************************");
        out += line("***       Unused stubs       ***");
        out += line("********************************");
        
        for(Invocation i:invocations) {
            out += line(i.toString());
            out += line(" stubbed: " + i.getLocation());
        }
        return print(out);
    }