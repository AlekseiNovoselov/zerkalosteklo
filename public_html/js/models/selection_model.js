define([
    // Libs
    'jquery',
    'backbone'

], function($, Backbone) {
    var selectionModel = Backbone.Model.extend({
            x: 0,
            y: 0,
            w : 0,
            h : 0,
            px : 0, // extra variables to dragging calculations
            py : 0,
            csize : 6, // resize cubes size
            csizeh : 10, // resize cubes size (on hover)
            bHow : [false, false, false, false], // hover statuses
            iCSize : [this.csize, this.csize, this.csize, this.csize], // resize cubes sizes
            bDrag : [false, false, false, false], // drag statuses
            bDragAll : false, // drag whole selection

        draw : function(k, image){
            var canvas = document.getElementById('panel');
            var ctx = canvas.getContext('2d');
            ctx.strokeStyle = '#000';
            ctx.lineWidth = 2;
            ctx.strokeRect(this.x, this.y, this.w, this.h);

            if (this.w > 0 && this.h > 0) {
                ctx.drawImage(image, this.x*k, this.y*k, this.w*k, this.h*k, this.x, this.y, this.w, this.h);
            }

            // draw resize cubes
            ctx.fillStyle = '#fff';
            ctx.fillRect(this.x - this.iCSize[0], this.y - this.iCSize[0], this.iCSize[0] * 2, this.iCSize[0] * 2);
            ctx.fillRect(this.x + this.w - this.iCSize[1], this.y - this.iCSize[1], this.iCSize[1] * 2, this.iCSize[1] * 2);
            ctx.fillRect(this.x + this.w - this.iCSize[2], this.y + this.h - this.iCSize[2], this.iCSize[2] * 2, this.iCSize[2] * 2);
            ctx.fillRect(this.x - this.iCSize[3], this.y + this.h - this.iCSize[3], this.iCSize[3] * 2, this.iCSize[3] * 2);
        },

        onUpHandler : function (e) {
            this.bDragAll = false;

            for (i = 0; i < 4; i++) {
                this.bDrag[i] = false;
            }
            this.px = 0;
            this.py = 0;
        },

        onDownHandler : function (e) {
            var canvas = document.getElementById('panel');
            var canvasOffset = $(canvas).offset();
            var canvasOffset = $(canvas).offset();
            var iMouseX = Math.floor(e.pageX - canvasOffset.left);
            var iMouseY = Math.floor(e.pageY - canvasOffset.top);

            this.px = iMouseX - this.x;
            this.py = iMouseY - this.y;

            if (this.bHow[0]) {
                this.px = iMouseX - this.x;
                this.py = iMouseY - this.y;
            }
            if (this.bHow[1]) {
                this.px = iMouseX - this.x - this.w;
                this.py = iMouseY - this.y;
            }
            if (this.bHow[2]) {
                this.px = iMouseX - this.x - this.w;
                this.py = iMouseY - this.y - this.h;
            }
            if (this.bHow[3]) {
                this.px = iMouseX - this.x;
                this.py = iMouseY - this.y - this.h;
            }


            if (iMouseX > this.x + this.csizeh && iMouseX < this.x+this.w - this.csizeh &&
                iMouseY > this.y + this.csizeh && iMouseY < this.y+this.h - this.csizeh) {

                this.bDragAll = true;
            }

            for (i = 0; i < 4; i++) {
                if (this.bHow[i]) {
                    this.bDrag[i] = true;
                }
            }

        },

        onmoveHanfler : function (e) {
            var canvas = document.getElementById('panel');
            var canvasOffset = $(canvas).offset();
            var iMouseX = Math.floor(e.pageX - canvasOffset.left);
            var iMouseY = Math.floor(e.pageY - canvasOffset.top);
            // in case of drag of whole selector
            if (this.bDragAll) {
                this.x = iMouseX - this.px;
                this.y = iMouseY - this.py;
            }

            for (var i = 0; i < 4; i++) {
                this.bHow[i] = false;
                this.iCSize[i] = this.csize;
            }

            // hovering over resize cubes
            if (iMouseX > this.x - this.csizeh && iMouseX < this.x + this.csizeh &&
                iMouseY > this.y - this.csizeh && iMouseY < this.y + this.csizeh) {

                this.bHow[0] = true;
                this.iCSize[0] = this.csizeh;
            }
            if (iMouseX > this.x + this.w-this.csizeh && iMouseX < this.x + this.w + this.csizeh &&
                iMouseY > this.y - this.csizeh && iMouseY < this.y + this.csizeh) {

                this.bHow[1] = true;
                this.iCSize[1] = this.csizeh;
            }
            if (iMouseX > this.x + this.w-this.csizeh && iMouseX < this.x + this.w + this.csizeh &&
                iMouseY > this.y + this.h-this.csizeh && iMouseY < this.y + this.h + this.csizeh) {

                this.bHow[2] = true;
                this.iCSize[2] = this.csizeh;
            }
            if (iMouseX > this.x - this.csizeh && iMouseX < this.x + this.csizeh &&
                iMouseY > this.y + this.h-this.csizeh && iMouseY < this.y + this.h + this.csizeh) {

                this.bHow[3] = true;
                this.iCSize[3] = this.csizeh;
            }

            // in case of dragging of resize cubes
            var iFW, iFH;
            if (this.bDrag[0]) {
                var iFX = iMouseX - this.px;
                var iFY = iMouseY - this.py;
                iFW = this.w + this.x - iFX;
                iFH = this.h + this.y - iFY;
            }
            if (this.bDrag[1]) {
                var iFX = this.x;
                var iFY = iMouseY - this.py;
                iFW = iMouseX - this.px - iFX;
                iFH = this.h + this.y - iFY;
            }
            if (this.bDrag[2]) {
                var iFX = this.x;
                var iFY = this.y;
                iFW = iMouseX - this.px - iFX;
                iFH = iMouseY - this.py - iFY;
            }
            if (this.bDrag[3]) {
                var iFX = iMouseX - this.px;
                var iFY = this.y;
                iFW = this.w + this.x - iFX;
                iFH = iMouseY - this.py - iFY;
            }

            if (iFW > this.csizeh * 2 && iFH > this.csizeh * 2) {
                this.w = iFW;
                this.h = iFH;

                this.x = iFX;
                this.y = iFY;
            }
        },

        initialize: function (_x, _y, _w, _h) {
            this.x = _x;
            this.y = _y;
            this.w = _w;
            this.h = _h;
            this.px = this.x;
            this.py = this.y;
        }
    });
    return selectionModel;
});
