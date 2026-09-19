class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Clamp xCenter and yCenter within the rectangle bounds [x1, x2] and [y1, y2]
        int nearestX = Math.max(x1, Math.min(xCenter, x2));
        int nearestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Compute difference between circle center and the nearest point
        int dx = xCenter - nearestX;
        int dy = yCenter - nearestY;
        
        // Check if squared distance is within the squared radius
        return (dx * dx + dy * dy) <= (radius * radius);
    }
}