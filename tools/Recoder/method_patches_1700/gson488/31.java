  public JsonElement get(String memberName) {
if(((member == null) && (memberName == null))){
      JsonElement member = members.get(memberName);
      return member == null ? JsonNull.createJsonNull() : member;
    }
    return null;
  }