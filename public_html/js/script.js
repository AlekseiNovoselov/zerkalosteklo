        var canvas, ctx;
        var image;
        var iMouseX, iMouseY = 1;
        var thisA;

        // define Selection constructor
        function Selection(x, y, w, h){
            this.x = x; // initial positions
            this.y = y;
            this.w = w; // and size
            this.h = h;

            this.px = x; // extra variables to dragging calculations
            this.py = y;

            this.k = 0;
            this.imageWidth = 0;
            this.imageHeight = 0;

            this.src = "";

            this.csize = 6; // resize cubes size
            this.csizeh = 10; // resize cubes size (on hover)

            this.bHow = [false, false, false, false]; // hover statuses
            this.iCSize = [this.csize, this.csize, this.csize, this.csize]; // resize cubes sizes
            this.bDrag = [false, false, false, false]; // drag statuses
            this.bDragAll = false; // drag whole selection
        }

        // define Selection draw method
        Selection.prototype.draw = function(k){

            ctx.strokeStyle = '#000';

            ctx.lineWidth = 2;
            ctx.strokeRect(this.x, this.y, this.w, this.h);

            // draw part of original image
            if (this.w > 0 && this.h > 0) {
                ctx.drawImage(image, this.x*k, this.y*k, this.w*k, this.h*k, this.x, this.y, this.w, this.h);
            }

            // draw resize cubes
            ctx.fillStyle = '#fff';
            ctx.fillRect(this.x - this.iCSize[0], this.y - this.iCSize[0], this.iCSize[0] * 2, this.iCSize[0] * 2);
            ctx.fillRect(this.x + this.w - this.iCSize[1], this.y - this.iCSize[1], this.iCSize[1] * 2, this.iCSize[1] * 2);
            ctx.fillRect(this.x + this.w - this.iCSize[2], this.y + this.h - this.iCSize[2], this.iCSize[2] * 2, this.iCSize[2] * 2);
            ctx.fillRect(this.x - this.iCSize[3], this.y + this.h - this.iCSize[3], this.iCSize[3] * 2, this.iCSize[3] * 2);
        }

        function drawScene() { // main drawScene function

            ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height); // clear canvas
            // draw source image
            ctx.drawImage(image, 0, 0, thisA.imageWidth, thisA.imageHeight);
            // and make it darker
            ctx.fillStyle = 'rgba(0, 0, 0, 0.5)';
            ctx.fillRect(0, 0,thisA.imageWidth, thisA.imageHeight);
            // draw selection
            thisA.draw(thisA.k);
        }

        $(function(){

            // creating canvas and context objects
            canvas = document.getElementById('panel');
            ctx = canvas.getContext('2d');

            // loading source image
            image = new Image();
            var imageWidth;
            var imageHeight;
            image.onload = function () {
            imageWidth = this.width;
            imageHeight = this.height;
                var kWidth = imageWidth/ctx.canvas.width;
                var kHeight = imageHeight/ctx.canvas.height;
                if (kWidth > 1) {
                    thisA.imageWidth = imageWidth/kWidth;
                    thisA.imageHeight = imageHeight/kWidth;
                    kHeight = imageHeight/ctx.canvas.height;
                    thisA.k = kWidth;
                }
                if (kHeight > 1) {
                    thisA.imageHeight = imageHeight/kHeight;
                    thisA.imageWidth = imageWidth/kHeight;
                    thisA.k = kHeight;
                }
                else {
                    thisA.imageWidth = imageWidth;
                    thisA.imageHeight = imageHeight;
                    thisA.k = 1;
                }
            };
            image.src = "../static/picture-1.jpg";
            // create initial selection
            thisA = new Selection(200, 200, 200, 200);
            thisA.src = "/static/picture-1.jpg";

            $('#panel').mousemove(function(e) { // binding mouse move event
                var canvasOffset = $(canvas).offset();
                iMouseX = Math.floor(e.pageX - canvasOffset.left);
                iMouseY = Math.floor(e.pageY - canvasOffset.top);

                // in case of drag of whole selector
                if (thisA.bDragAll) {
                    thisA.x = iMouseX - thisA.px;
                    thisA.y = iMouseY - thisA.py;
                }

                for (i = 0; i < 4; i++) {
                    thisA.bHow[i] = false;
                    thisA.iCSize[i] = thisA.csize;
                }

                // hovering over resize cubes
                if (iMouseX > thisA.x - thisA.csizeh && iMouseX < thisA.x + thisA.csizeh &&
                    iMouseY > thisA.y - thisA.csizeh && iMouseY < thisA.y + thisA.csizeh) {

                    thisA.bHow[0] = true;
                    thisA.iCSize[0] = thisA.csizeh;
                }
                if (iMouseX > thisA.x + thisA.w-thisA.csizeh && iMouseX < thisA.x + thisA.w + thisA.csizeh &&
                    iMouseY > thisA.y - thisA.csizeh && iMouseY < thisA.y + thisA.csizeh) {

                    thisA.bHow[1] = true;
                    thisA.iCSize[1] = thisA.csizeh;
                }
                if (iMouseX > thisA.x + thisA.w-thisA.csizeh && iMouseX < thisA.x + thisA.w + thisA.csizeh &&
                    iMouseY > thisA.y + thisA.h-thisA.csizeh && iMouseY < thisA.y + thisA.h + thisA.csizeh) {

                    thisA.bHow[2] = true;
                    thisA.iCSize[2] = thisA.csizeh;
                }
                if (iMouseX > thisA.x - thisA.csizeh && iMouseX < thisA.x + thisA.csizeh &&
                    iMouseY > thisA.y + thisA.h-thisA.csizeh && iMouseY < thisA.y + thisA.h + thisA.csizeh) {

                    thisA.bHow[3] = true;
                    thisA.iCSize[3] = thisA.csizeh;
                }

                // in case of dragging of resize cubes
                var iFW, iFH;
                if (thisA.bDrag[0]) {
                    var iFX = iMouseX - thisA.px;
                    var iFY = iMouseY - thisA.py;
                    iFW = thisA.w + thisA.x - iFX;
                    iFH = thisA.h + thisA.y - iFY;
                }
                if (thisA.bDrag[1]) {
                    var iFX = thisA.x;
                    var iFY = iMouseY - thisA.py;
                    iFW = iMouseX - thisA.px - iFX;
                    iFH = thisA.h + thisA.y - iFY;
                }
                if (thisA.bDrag[2]) {
                    var iFX = thisA.x;
                    var iFY = thisA.y;
                    iFW = iMouseX - thisA.px - iFX;
                    iFH = iMouseY - thisA.py - iFY;
                }
                if (thisA.bDrag[3]) {
                    var iFX = iMouseX - thisA.px;
                    var iFY = thisA.y;
                    iFW = thisA.w + thisA.x - iFX;
                    iFH = iMouseY - thisA.py - iFY;
                }

                if (iFW > thisA.csizeh * 2 && iFH > thisA.csizeh * 2) {
                    thisA.w = iFW;
                    thisA.h = iFH;

                    thisA.x = iFX;
                    thisA.y = iFY;
                }

                drawScene();
            });

            $('#panel').mousedown(function(e) { // binding mousedown event
                var canvasOffset = $(canvas).offset();
                iMouseX = Math.floor(e.pageX - canvasOffset.left);
                iMouseY = Math.floor(e.pageY - canvasOffset.top);

                thisA.px = iMouseX - thisA.x;
                thisA.py = iMouseY - thisA.y;

                if (thisA.bHow[0]) {
                    thisA.px = iMouseX - thisA.x;
                    thisA.py = iMouseY - thisA.y;
                }
                if (thisA.bHow[1]) {
                    thisA.px = iMouseX - thisA.x - thisA.w;
                    thisA.py = iMouseY - thisA.y;
                }
                if (thisA.bHow[2]) {
                    thisA.px = iMouseX - thisA.x - thisA.w;
                    thisA.py = iMouseY - thisA.y - thisA.h;
                }
                if (thisA.bHow[3]) {
                    thisA.px = iMouseX - thisA.x;
                    thisA.py = iMouseY - thisA.y - thisA.h;
                }


                if (iMouseX > thisA.x + thisA.csizeh && iMouseX < thisA.x+thisA.w - thisA.csizeh &&
                    iMouseY > thisA.y + thisA.csizeh && iMouseY < thisA.y+thisA.h - thisA.csizeh) {

                    thisA.bDragAll = true;
                }

                for (i = 0; i < 4; i++) {
                    if (thisA.bHow[i]) {
                        thisA.bDrag[i] = true;
                    }
                }
            });

            $('#panel').mouseup(function(e) { // binding mouseup event
                thisA.bDragAll = false;

                for (i = 0; i < 4; i++) {
                    thisA.bDrag[i] = false;
                }
                thisA.px = 0;
                thisA.py = 0;
            });

            drawScene();
 });

function getResults() {
    var temp_ctx, temp_canvas;
    temp_canvas = document.createElement('canvas');
    temp_ctx = temp_canvas.getContext('2d');
    temp_canvas.width = thisA.w;
    temp_canvas.height = thisA.h;
    temp_ctx.drawImage(image, thisA.x*thisA.k, thisA.y*thisA.k, thisA.w*thisA.k, thisA.h*thisA.k, 0, 0, thisA.w, thisA.h);
    var vData = temp_canvas.toDataURL();
    $('#crop_result').attr('src', vData);
    $('#results h2').text('Well done, we have prepared our cropped image, now you can save it if you wish');
}

function loadSelectedPicture() {
    var x = document.getElementById("mySelect").selectedIndex;
    var y = document.getElementById("mySelect").options;
    var src = "/static/" +  y[x].text;
    thisA.src = src;
    thisA.serverSrc = "./public_html" + src;
    console.log(src);
    image.src = src;
}

function saveImage() {
    var person = {
                resultX: 200,
                resultY: 200,
                x: thisA.x,
                y: thisA.y,
                width: thisA.w,
                height: thisA.h
            };

            var path = "./public_html" + thisA.src;

            console.log(person);
            $.ajax({
                url: '/cropImage',
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    $('#target').html(data.msg);
                },
                data: JSON.stringify({resultX: 200, resultY: 200, x: thisA.x*thisA.k, y: thisA.y*thisA.k,                                     width: thisA.w*thisA.k,
                                      height: thisA.h*thisA.k, originPath: path})
            });
        }
