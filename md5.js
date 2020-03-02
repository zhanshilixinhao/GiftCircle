function ss(){
    var bridge = function(text) {
        var tool = this;
        // var opts = parseOptions(tool);
        // if (!opts) {
        //     return "";
        // }
        //
        // if (text.length == 0) {
        //     return "";
        // }

        var bytes = convertTextToBytes(text);
        console.log(bytes)
        bytes = convertTextToBytes1(text);
        console.log(bytes)
        var output = [];

        for (var i = 0; i < bytes.length; i++) {
            var byte = bytes[i].toString(opts.base);
            if (opts.padding) {
                var byte = addPadding(byte, opts.base);
            }
            if (opts.prefix) {
                var byte = addPrefix(byte, opts.base);
            }
            output.push(byte);
        }

        return output.join(opts.separator);
    }

    function convertTextToBytes(text) {
        var bytes = [];
        var encoded = utf8.encode(text);
        for (var i = 0; i < encoded.length; i++) {
            var byte = encoded[i].charCodeAt(0);
            bytes.push(byte);
        }
        return bytes;
    }

    function convertTextToBytes1(text) {
        var bytes = [];
        var encoded = Utf8Encode(text);
        for (var i = 0; i < encoded.length; i++) {
            var byte = encoded[i].charCodeAt(0);
            bytes.push(byte);
        }
        return bytes;
    }

    function Utf8Encode(string) {
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }
        }
        return utftext;
    }

    function addPadding(byte, base) {
        if (base == 2) var len = 8;
        else if (base == 8) var len = 3;
        else if (base == 16) var len = 2;

        while (byte.length < len) {
            byte = '0' + byte;
        }
        return byte;
    }

    function addPrefix(byte, base) {
        if (base == 2) var prefix = "0b";
        else if (base == 8) var prefix = "o";
        else if (base == 16) var prefix = "0x";
        else var prefix = "";

        return prefix + byte;
    }

    function parseOptions(tool) {
        var options = tool.options.get();
        var error   = function(a, b) { tool.output.showNegativeBadge(a, b, -1); }

        var separator = options['separator'];
        if (separator == "\\n") separator = "\n";

        var base = options["base"];
        if (base == "binary") {
            var base = 2;
        }
        else if (base == "octal") {
            var base = 8;
        }
        else if (base == "decimal") {
            var base = 10;
        }
        else if (base == "hexadecimal") {
            var base = 16;
        }
        else if (base == "custom") {
            var base = options['custom-base'];
            if (!/^\d+$/.test(base)) {
                error("Invalid Base Value", "The base value that you entered is not an integer.");
                return false;
            }
            base = parseInt(base);
            if (base > 36) {
                error("Invalid Base Value", "The base value must be less than or equal to 36, but you specified {0}.".format(base));
                return false;
            }
            if (base < 2) {
                error("Invalid Base Value", "The base value must be greater than or equal to 2, but you specified {0}.".format(base));
                return false;
            }
        }

        return {
            separator : separator,
            base      : base,
            padding   : options["padding"],
            prefix    : options["prefix"]
        };
    }

    return {
        converter: bridge,
        config: {}
    }

}
