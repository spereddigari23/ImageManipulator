import java.awt.Color;
class Driver{
    private Picture original;
    private Picture collage;
    private int collageDimension;
    private int tileDimension;
    //INITIALIZES COLLAGE WITH FILENAME OF THE IMAGE, FOR THIS COLLAGE, EACH TILE WILL BE 100 PIXELS AND THE COLLAGE LENGTH WILL BE 4 TILES.
    //SCALES THE ORIGINAL IMAGE TO FIT THE NEW COLLAGE WHICH CAN THEN BE MANIPULATED
    public Driver (String filename) {
        this.tileDimension = 100;
        this.collageDimension = 4;
        Picture firstPicture = new Picture(filename);
        this.original = firstPicture;
        Picture collage1 = new Picture(400, 400);
        for(int i = 0; i<400; i++){
            for(int j = 0; j<400; j++){
                int oldColumn = (i*this.original.width())/400;
                int oldRow = (j*this.original.height())/400;
                while(oldColumn>firstPicture.width()-1){
                    oldColumn = oldColumn-1;
                }
                while(oldRow>firstPicture.height()-1){
                    oldRow = oldRow-1;
                }
                Color color = this.original.get(oldColumn, oldRow);
                collage1.set(i, j, color);
            }
        }
        this.collage = collage1;
    }
    //INITIALIZES COLLAGE WITH THE ARGUMENT IMAGE, FOR THIS COLLAGE, THE TILE DIMENSION AND COLLAGE DIMENSIONS WILL ALSO BE ARGUMENTS
    //SCALES THE ORIGINAL IMAGE TO FIT THE NEW COLLAGE WHICH CAN THEN BE MANIPULATED
    public Driver (String filename, int td, int cd) {
        this.collageDimension = cd;
        this.tileDimension = td;
        Picture firstPicture = new Picture(filename);
        this.original = firstPicture;
        Picture collage1 = new Picture(td*cd, td*cd);
        for(int i = 0; i<td*cd; i++){
            for(int j = 0; j<td*cd; j++){
                int oldColumn = (i*this.original.width())/(td*cd);
                int oldRow = (j*this.original.height())/(td*cd);
                while(oldColumn>firstPicture.width()-1){
                    oldColumn = oldColumn-1;
                }
                while(oldRow>firstPicture.height()-1){
                    oldRow = oldRow-1;
                }
                Color color = this.original.get(oldColumn, oldRow);
                collage1.set(i, j, color);
            }
        }
        this.collage = collage1;
    }
    public int getCollageDimension() {
        return this.collageDimension;
    }
    public int getTileDimension() {
        return this.tileDimension;
    }
    public Picture getOriginalPicture() {
        return this.original;
    }
    public Picture getCollagePicture() {
        return this.collage;
    }
    public void showOriginalPicture() {
        this.original.show();
    }
    public void showCollagePicture() {
        this.collage.show();
    }
    //TAKES A NEW IMAGE AND THE ROW AND COLUMN VALUE OF THE COLLAGE TO REPLACE THE IMAGE AT
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        Picture newTile = new Picture(filename);
        int startingCol = collageCol*(this.collage.width()/this.collageDimension);
        int startingRow = collageRow*(this.collage.height()/this.collageDimension);
        System.out.println(this.tileDimension);
        for(int i = startingCol; i<startingCol+this.tileDimension; i++){
            for(int j = startingRow; j<startingRow+this.tileDimension; j++){
                int hold = i;
                int hold2 = j;
                while(hold>this.tileDimension){
                    hold = hold-this.tileDimension;
                }
                while(hold2>this.tileDimension){
                    hold2 = hold2-this.tileDimension;
                }
                int oldColumn = (hold*newTile.width())/(this.tileDimension);
                int oldRow = (hold2*newTile.height())/(this.tileDimension);
                while(oldColumn>newTile.width()-1){
                    oldColumn = oldColumn-1;
                }
                while(oldRow>newTile.height()-1){
                    oldRow = oldRow-1;
                }
                Color color = newTile.get(oldColumn, oldRow);
                this.collage.set(i, j, color);
            }
        }
    }
    //THIS METHOD WILL MAKE A COLLAGE OF THE ORIGINAL PICTURE WITH THE COLLAGE DIMENSION NUMBER OF TILES
    public void makeCollage () {
        int size = this.collageDimension*this.tileDimension;
        Picture collagefinale = new Picture(size, size);
        for(int i = 0; i<collagefinale.width(); i++){
            for(int j = 0; j<collagefinale.height(); j++){
                int hold = i;
                int hold2 = j;
                while(hold>this.tileDimension){
                    hold = hold-this.tileDimension;
                }
                while(hold2>this.tileDimension){
                    hold2 = hold2-this.tileDimension;
                }
                int oldColumn = (hold*this.original.width())/(this.tileDimension);
                int oldRow = (hold2*this.original.height())/(this.tileDimension);
                while(oldColumn>original.width()-1){
                    oldColumn = oldColumn-1;
                }
                while(oldRow>original.height()-1){
                    oldRow = oldRow-1;
                }
                Color color = original.get(oldColumn, oldRow);
                collagefinale.set(i, j, color);
            }
        }
        this.collage = collagefinale;
    }
    //TAKES A COLUMN AND ROW VALUE AND BLURS THAT TILE BY A SCALE OF 2
    public void blurTileBy2(int collageCol, int collageRow){
        int startingCol = collageCol*(this.collage.width()/this.collageDimension);
        int startingRow = collageRow*(this.collage.height()/this.collageDimension);
        for(int i = startingCol; i<startingCol+this.tileDimension; i++){
            for(int j = startingRow; j<startingRow+this.tileDimension; j++){
                int hold = i;
                int hold2 = j;
                if(hold%2 == 0){
                    hold = hold+1;
                }else{
                    hold = hold-1;
                }
                if(hold2%2 == 0){
                    hold = hold+1;
                }else{
                    hold = hold-1;
                }
                int oldColumn = (hold*this.original.width())/(this.tileDimension);
                int oldRow = (hold2*this.original.width())/(this.tileDimension);
                while(oldColumn<0){
                    oldColumn = oldColumn+1;
                }
                while(oldRow<0){
                    oldRow = oldRow+1;
                }
                while(oldColumn>original.width()-1){
                    oldColumn = oldColumn-1;
                }
                while(oldRow>original.height()-1){
                    oldRow = oldRow-1;
                }
                Color color = this.original.get(oldColumn, oldRow);
                this.collage.set(i, j, color);
            }
        }
    }
    //TAKES A COLLAGE COLUMN AND ROW NUMBER AND GIVES THE TILE THE EFFECT OF A DOUBLE IMAGE.
    public void doubleEffect(int collageCol, int collageRow){
        int startingCol = collageCol*(this.collage.width()/this.collageDimension);
        int startingRow = collageRow*(this.collage.height()/this.collageDimension);
        for(int i = startingCol; i<startingCol+this.tileDimension; i++){
            for(int j = startingRow; j<startingRow+this.tileDimension; j++){
                int hold = i;
                int hold2 = j;
                if(hold%2 == 0){
                    hold = hold+1;
                }else{
                    hold = hold-1;
                }
                if(hold2%2 == 0){
                    hold = hold+41;
                }else{
                    hold = hold-4;
                }
                int oldColumn = (hold*this.original.width())/(this.tileDimension);
                int oldRow = (hold2*this.original.width())/(this.tileDimension);
                while(oldColumn<0){
                    oldColumn = oldColumn+1;
                }
                while(oldRow<0){
                    oldRow = oldRow+1;
                }
                while(oldColumn>original.width()-1){
                    oldColumn = oldColumn-((startingCol+this.tileDimension-i));
                }
                while(oldRow>original.height()-1){
                    oldRow = oldRow-((startingRow+this.tileDimension-i));
                }
                Color color = this.original.get(oldColumn, oldRow);
                this.collage.set(i, j, color);
            }
        }
    }
    //WILL TAKE A COLOR, A COLUMN, AND ROW VALUE WHICH WILL LUMINATE THE SPECIFIED TILE IN THE GIVEN COLOR. 
    public void colorizeTile (String component,  int collageCol, int collageRow) {
        String value = component.toLowerCase();
        int startingCol = collageCol*(this.collage.width()/this.collageDimension);
        int startingRow = collageRow*(this.collage.height()/this.collageDimension);
        for(int i = startingCol; i<startingCol+this.tileDimension; i++){
            for(int j = startingRow; j<startingRow+this.tileDimension; j++){
                Color color = this.collage.get(i, j);
                if(value.equals("green")){
                    int g = color.getGreen();
                    this.collage.set(i, j, new Color(0, g, 0));
                }else if(value.equals("red")){
                    int r = color.getRed();
                    this.collage.set(i, j, new Color(r, 0, 0));
                }else if(value.equals("blue")){
                    int b = color.getBlue();
                    this.collage.set(i, j, new Color(0, 0, b));
                }
            }
        }
    }
    //TAKES A ROW AND COLUMN VALUE AND SHADES THAT TILE GRAY
    public void grayscaleTile (int collageCol, int collageRow) {
        int startingCol = collageCol*(this.collage.width()/this.collageDimension);
        int startingRow = collageRow*(this.collage.height()/this.collageDimension);
        for(int i = startingCol; i<startingCol+this.tileDimension; i++){
            for(int j = startingRow; j<startingRow+this.tileDimension; j++){
                Color color = this.collage.get(i, j);
                Color finale = Luminance.toGray(color);
                this.collage.set(i, j, finale);
            }
        }
    }
    //MAIN METHOD - FREE TO MANIPULATE AND TOY WITH

    public static void main (String[] args) {
        Driver art = new Driver("Jordan.jpg", 300, 3);
        art.makeCollage();
        art.replaceTile("Kobe.jpg", 0, 0);
        art.doubleEffect(0, 0);
        art.showCollagePicture();
    }
}