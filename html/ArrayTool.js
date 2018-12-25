/*
需求：编写一个js文件，在js文件中自定义一个数组工具对象，
该工具对象要有一个找到最大值的方法，与找元素对应的索引值的方法。
*/

//创建ArrayTool的对象

var tool = new ArrayTool();

function ArrayTool() {
    // 找最大值
    this.getMax = function(arr) {
        var max = arr[0];
        for (var i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // 找元素的索引值
    this.searchEle = function(arr, target) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 选择排序
    this.selectSort = function(arr) {
        for (var i = 0; i < arr.length - 1; i++) {
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    var temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    // 冒泡排序
    this.bubbleSort = function(arr) {
        for (var i = 0; i < arr.length - 1; i++) {
            for (var j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    var temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
