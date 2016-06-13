//Modify the code below to define the element's behavior.
//
//Example:  Change the line
//  y += printCenter(textline,y);
//to
//  y += 2*printCenter(textline,y);
//and observe the element preview.

int x = 0;
int y=textHeight();
int dividerWidth = 10;
int dividerLeft = (width - dividerWidth) / 2;
int dividerRight = (width + dividerWidth) / 2;
int dockerHeight = 12;
int dockerWidth = dockerHeight;
int dockSpacing = 8;
int textX = 0;
ArrayList<String> textLeft = new ArrayList<String>();
ArrayList<String> textRight = new ArrayList<String>();
ArrayList<String> currentTextSide = textLeft;

drawRectangle(dockerWidth,0,width - 2*dockerWidth,height);


for(String textline : textlines) {
    if (textline.equals("--")) {
        currentTextSide = textRight;
    }
    if (!textline.isEmpty()
        && !textline.equals("--")
        && textline.indexOf("'") != 0)
        currentTextSide.add(textline);
}

for (String lt : textLeft) {
    y += print(lt, dockerWidth + 4, y);
}

y = textHeight();
if (textLeft.size() > 0) {
    textX = (width + dividerWidth) / 2 + 4 ;
} else {
    textX = dockerWidth + 10 + dividerWidth + 4;
    dividerLeft = dockerWidth + 10;
    dividerRight = dockerWidth + 10 + dividerWidth;

}
for (String rt : textRight) {
    y += print(rt, textX, y);
}

drawLine(dividerLeft, 0, dividerRight, height / 2);
drawLine(dividerRight, height / 2, dividerLeft, height);


for(String textline : textlines) {
    if (textline.indexOf("'lb") == 0) {
        int rightDockers = Integer.valueOf(textline.substring(4));
        int rightDockersHeight = rightDockers * dockerHeight + (rightDockers - 1) * dockSpacing;
        int startDockHeight = (height - rightDockersHeight) / 2;
        for (int i = 0; i < rightDockers; i++) {
            int addY = i * (dockerHeight + dockSpacing);
            drawRectangle(0, startDockHeight + addY, dockerWidth, dockerHeight);
        }
    } else if (textline.indexOf("'rb") == 0) {
        int leftDockers = Integer.valueOf(textline.substring(4));
        int leftDockersHeight = leftDockers * dockerHeight + (leftDockers - 1) * dockSpacing;
        int startDockHeight = (height - leftDockersHeight) / 2;
        for (int i = 0; i < leftDockers; i++) {
            int addY = i * (dockerHeight + dockSpacing);
            drawRectangle(width-dockerWidth, startDockHeight + addY, dockerWidth, dockerHeight);
        }
    }
}

