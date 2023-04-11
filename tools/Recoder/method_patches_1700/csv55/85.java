    public boolean isCommentingEnabled() {
if((this.commentStart == null)){
return true;}        return this.commentStart > DISABLED;    }