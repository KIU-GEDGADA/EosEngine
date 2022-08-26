package graphics;

public class Face {

    private IndexGroup[] idxGroups;

    public Face(String v1, String v2, String v3) {
        idxGroups = new IndexGroup[3];
        idxGroups[0] = parseLine(v1);
        idxGroups[1] = parseLine(v2);
        idxGroups[2] = parseLine(v3);
    }

    private IndexGroup parseLine(String line) {
        IndexGroup idxGroup = new IndexGroup();
        String[] lineTokens = line.split("/");
        int length = lineTokens.length;
        idxGroup.vertexIndex = Integer.parseInt(lineTokens[0].strip()) - 1;
        if (length > 1) {
            idxGroup.textureIndex = lineTokens[1].strip().length() > 0 ? Integer.parseInt(lineTokens[1].strip()) - 1 : -1;
            if (length > 2) {
                idxGroup.normalIndex = Integer.parseInt(lineTokens[2].strip()) - 1;
            }
        }

        return idxGroup;
    }

    public IndexGroup[] getFaceVertexIndices() {
        return idxGroups;
    }

}