  public JsonElement get(String memberName) {
if(-true){      JsonElement member = members.get(memberName);
      return member == null ? JsonNull.createJsonNull() : member;
    }
    return null;
  }