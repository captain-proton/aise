        /****CUSTOM_CODE START****/
// This element contains text on two sides and is divided by --
// Text before the -- is show on the left side, text after on the ride side.
// Use 'rb=X and 'lb=Y to show a arbitrary number of docks on the left and
// right side of the bounding box
int x = 0;
int y=textHeight();
int dockerHeight = 12;
int dockerWidth = dockerHeight;
int dockSpacing = 8;
int dividerWidth = 10;
int dividerLeft = (int) ((width - dividerWidth) * 0.4);
int dividerRight = (int) ((width + dividerWidth) * 0.4);
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

if (textLeft.size() == 0 && textRight.size() > 0) {
    dividerLeft = dockerWidth + 10;
    dividerRight = dockerWidth + 10 + dividerWidth;
} else if (textLeft.size() > 0 && textRight.size() == 0) {
    dividerLeft = width - (dockerWidth + 10 + dividerWidth);
    dividerRight = width - (dockerWidth + 10);
}

y = textHeight();
textX = dividerRight + 4;

for (String rt : textRight) {
    y += print(rt, textX, y);
}

drawLine(dividerLeft, 0, dividerRight, height / 2);
drawLine(dividerRight, height / 2, dividerLeft, height);


for(String textline : textlines) {

    int dockerCount = 0;
    int dockHeight = 0;
    int startDockHeight = 0;
    if ((textline.indexOf("'lb") == 0 || textline.indexOf("'rb") == 0)
        && textline.length() > 4) {

        String countText = textline.substring(4);
        if (countText.matches("[0-9]+")) {
            dockerCount = Integer.valueOf(countText);
            dockHeight =dockerCount * dockerHeight + (dockerCount - 1) * dockSpacing;
            startDockHeight = (height - dockHeight) / 2;
        }
    }

    if (textline.indexOf("'lb") == 0) {
        for (int i = 0; i < dockerCount; i++) {
            int addY = i * (dockerHeight + dockSpacing);
            drawRectangle(0, startDockHeight + addY, dockerWidth, dockerHeight);
        }
    } else if (textline.indexOf("'rb") == 0) {
        for (int i = 0; i < dockerCount; i++) {
            int addY = i * (dockerHeight + dockSpacing);
            drawRectangle(width-dockerWidth, startDockHeight + addY, dockerWidth, dockerHeight);
        }
    }
}
