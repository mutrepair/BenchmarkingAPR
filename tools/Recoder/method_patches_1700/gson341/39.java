  public JsonArray getAsJsonArray() {
if("null"){      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }