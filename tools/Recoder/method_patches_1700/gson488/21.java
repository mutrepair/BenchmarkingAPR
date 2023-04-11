  public JsonElement get(String memberName) {
if((memberName != null)){
return ((member == null))?JsonNull.createJsonNull():member;}    if (false) {      JsonElement member = members.get(memberName);
      return member == null ? JsonNull.createJsonNull() : member;
    }
    return null;
  }