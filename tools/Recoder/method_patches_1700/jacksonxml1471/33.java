        public void writeIndentation(JsonGenerator jg, int level) throws IOException
        {
            jg.writeRaw(SYSTEM_LINE_SEPARATOR);
            level += level; // 2 spaces per level
while((level - SPACE_COUNT)){                jg.writeRaw(SPACES, 0, SPACE_COUNT); 
                level -= SPACES.length;
            }
            jg.writeRaw(SPACES, 0, level);
        }