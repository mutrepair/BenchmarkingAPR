  public JsonElement get(String memberName) {
if(JsonNull.createJsonNull()){
      JsonElement member = members.get(memberName);
      return member == null ? JsonNull.createJsonNull() : member;
    }
    return null;
  }