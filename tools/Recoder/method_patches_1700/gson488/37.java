  public JsonElement get(String memberName) {
if((memberName || (memberName == null))){
      JsonElement member = members.get(memberName);
      return member == null ? JsonNull.createJsonNull() : member;
    }
    return null;
  }