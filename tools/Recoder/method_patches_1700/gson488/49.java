  public JsonElement get(String memberName) {
if(((memberName == null) && (memberName == null))){
return null;}    if (false) {      JsonElement member = members.get(memberName);
      return member == null ? JsonNull.createJsonNull() : member;
    }
    return null;
  }