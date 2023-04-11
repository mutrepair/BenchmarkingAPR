    public void execute(final E input) {
        for (int i = 0; i < iPredicates.length; i++) {
if(input){
                iClosures[i].execute(input);
                return;
            }
        }
        iDefault.execute(input);
    }