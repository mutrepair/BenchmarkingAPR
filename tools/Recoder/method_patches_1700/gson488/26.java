  public JsonElement get(String memberName) {
super.get(memberName);
    if (false) {      JsonElement member = members.get(memberName);
      return member == null ? JsonNull.createJsonNull() : member;
    }
    return null;
  }