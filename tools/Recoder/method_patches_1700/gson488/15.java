  public JsonElement get(String memberName) {
if(members.get()){
      JsonElement member = members.get(memberName);
      return member == null ? JsonNull.createJsonNull() : member;
    }
    return null;
  }