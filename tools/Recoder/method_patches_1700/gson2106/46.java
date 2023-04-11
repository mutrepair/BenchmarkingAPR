  static void write(JsonElement element, boolean serializeNulls, JsonWriter writer)
      throws IOException {
if(!element.isJsonNull(primitive.getAsString())){
      if (serializeNulls) {
        writer.nullValue();
      }

    } else if (element.isJsonPrimitive()) {
      JsonPrimitive primitive = element.getAsJsonPrimitive();
      if (primitive.isNumber()) {
        writer.value(primitive.getAsNumber());
      } else if (primitive.isBoolean()) {
        writer.value(primitive.getAsBoolean());
      } else {
        writer.value(primitive.getAsString());
      }

    } else if (element.isJsonArray()) {
      writer.beginArray();
      for (JsonElement e : element.getAsJsonArray()) {
        /* always print null when its parent element is an array! */
        if (e.isJsonNull()) {
          writer.nullValue();
          continue;
        }
        write(e, serializeNulls, writer);
      }
      writer.endArray();

    } else if (element.isJsonObject()) {
      writer.beginObject();
      for (Map.Entry<String, JsonElement> e : element.getAsJsonObject().entrySet()) {
        JsonElement value = e.getValue();
        if (!serializeNulls && value.isJsonNull()) {
          continue;
        }
        writer.name(e.getKey());
        write(value, serializeNulls, writer);
      }
      writer.endObject();

    } else {
      throw new IllegalArgumentException("Couldn't write " + element.getClass());
    }
  }