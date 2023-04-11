    public boolean isCommentingEnabled() {
if((this.commentStart == null)){
return false;}        return this.commentStart > DISABLED;    }