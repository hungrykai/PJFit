/* jQuery v3.5.1 | (c) JS Foundation and other contributors | jquery.org/license */
!function (a, b) {
    "object" == typeof module && "object" == typeof module.exports ? module.exports = a.document ? b(a, !0) : function (c) {
        if (!c.document) {
            throw new Error("jQuery requires a window with a document")
        }
        return b(c)
    } : b(a)
}("undefined" != typeof window ? window : this, function (aw, aH) {
    var b9 = [], bX = Object.getPrototypeOf, b3 = b9.slice, aT = b9.flat ? function (a) {
            return b9.flat.call(a)
        } : function (a) {
            return b9.concat.apply([], a)
        }, cg = b9.push, a5 = b9.indexOf, bz = {}, bF = bz.toString, cm = bz.hasOwnProperty, ah = cm.toString,
        bm = ah.call(Object), cF = {}, bs = function (a) {
            return "function" == typeof a && "number" != typeof a.nodeType
        }, cz = function (a) {
            return null != a && a === a.window
        }, aI = aw.document, av = {type: !0, src: !0, nonce: !0, noModule: !0};

    function an(a, g, c) {
        var f, b, d = (c = c || aI).createElement("script");
        if (d.text = a, g) {
            for (f in av) {
                (b = g[f] || g.getAttribute && g.getAttribute(f)) && d.setAttribute(f, b)
            }
        }
        c.head.appendChild(d).parentNode.removeChild(d)
    }

    function cs(a) {
        return null == a ? a + "" : "object" == typeof a || "function" == typeof a ? bz[bF.call(a)] || "object" : typeof a
    }

    var aN = "3.5.1", b4 = function (a, b) {
        return new b4.fn.init(a, b)
    };

    function bL(a) {
        var c = !!a && "length" in a && a.length, b = cs(a);
        return !bs(a) && !cz(a) && ("array" === b || 0 === c || "number" == typeof c && 0 < c && c - 1 in a)
    }

    b4.fn = b4.prototype = {
        jquery: aN, constructor: b4, length: 0, toArray: function () {
            return b3.call(this)
        }, get: function (a) {
            return null == a ? b3.call(this) : a < 0 ? this[a + this.length] : this[a]
        }, pushStack: function (a) {
            var b = b4.merge(this.constructor(), a);
            return b.prevObject = this, b
        }, each: function (a) {
            return b4.each(this, a)
        }, map: function (a) {
            return this.pushStack(b4.map(this, function (b, c) {
                return a.call(b, c, b)
            }))
        }, slice: function () {
            return this.pushStack(b3.apply(this, arguments))
        }, first: function () {
            return this.eq(0)
        }, last: function () {
            return this.eq(-1)
        }, even: function () {
            return this.pushStack(b4.grep(this, function (a, b) {
                return (b + 1) % 2
            }))
        }, odd: function () {
            return this.pushStack(b4.grep(this, function (a, b) {
                return b % 2
            }))
        }, eq: function (a) {
            var c = this.length, b = +a + (a < 0 ? c : 0);
            return this.pushStack(0 <= b && b < c ? [this[b]] : [])
        }, end: function () {
            return this.prevObject || this.constructor()
        }, push: cg, sort: b9.sort, splice: b9.splice
    }, b4.extend = b4.fn.extend = function () {
        var c, m, g, j, d, h, b = arguments[0] || {}, k = 1, p = arguments.length, f = !1;
        for ("boolean" == typeof b && (f = b, b = arguments[k] || {}, k++), "object" == typeof b || bs(b) || (b = {}), k === p && (b = this, k--); k < p; k++) {
            if (null != (c = arguments[k])) {
                for (m in c) {
                    j = c[m], "__proto__" !== m && b !== j && (f && j && (b4.isPlainObject(j) || (d = Array.isArray(j))) ? (g = b[m], h = d && !Array.isArray(g) ? [] : d || b4.isPlainObject(g) ? g : {}, d = !1, b[m] = b4.extend(f, h, j)) : void 0 !== j && (b[m] = j))
                }
            }
        }
        return b
    }, b4.extend({
        expando: "jQuery" + (aN + Math.random()).replace(/\D/g, ""), isReady: !0, error: function (a) {
            throw new Error(a)
        }, noop: function () {
        }, isPlainObject: function (a) {
            var c, b;
            return !(!a || "[object Object]" !== bF.call(a)) && (!(c = bX(a)) || "function" == typeof (b = cm.call(c, "constructor") && c.constructor) && ah.call(b) === bm)
        }, isEmptyObject: function (a) {
            var b;
            for (b in a) {
                return !1
            }
            return !0
        }, globalEval: function (a, c, b) {
            an(a, {nonce: c && c.nonce}, b)
        }, each: function (a, d) {
            var b, c = 0;
            if (bL(a)) {
                for (b = a.length; c < b; c++) {
                    if (!1 === d.call(a[c], c, a[c])) {
                        break
                    }
                }
            } else {
                for (c in a) {
                    if (!1 === d.call(a[c], c, a[c])) {
                        break
                    }
                }
            }
            return a
        }, makeArray: function (a, c) {
            var b = c || [];
            return null != a && (bL(Object(a)) ? b4.merge(b, "string" == typeof a ? [a] : a) : cg.call(b, a)), b
        }, inArray: function (a, c, b) {
            return null == c ? -1 : a5.call(c, a, b)
        }, merge: function (a, f) {
            for (var c = +f.length, d = 0, b = a.length; d < c; d++) {
                a[b++] = f[d]
            }
            return a.length = b, a
        }, grep: function (c, j, f) {
            for (var h = [], d = 0, g = c.length, b = !f; d < g; d++) {
                !j(c[d], d) !== b && h.push(c[d])
            }
            return h
        }, map: function (c, j, f) {
            var h, d, g = 0, b = [];
            if (bL(c)) {
                for (h = c.length; g < h; g++) {
                    null != (d = j(c[g], g, f)) && b.push(d)
                }
            } else {
                for (g in c) {
                    null != (d = j(c[g], g, f)) && b.push(d)
                }
            }
            return aT(b)
        }, guid: 1, support: cF
    }), "function" == typeof Symbol && (b4.fn[Symbol.iterator] = b9[Symbol.iterator]), b4.each("Boolean Number String Function Array Date RegExp Object Error Symbol".split(" "), function (a, b) {
        bz["[object " + b + "]"] = b.toLowerCase()
    });
    var aB = function (du) {
        var c2, cZ, cS, dx, dg, dc, c6, c9, dV, dP, dn, dM, cW, ct, c3, dS, dI, cV, d1, dJ = "sizzle" + 1 * new Date,
            dA = du.document, dl = 0, dF = 0, dr = dR(), dY = dR(), cQ = dR(), dv = dR(), c0 = function (a, b) {
                return a === b && (dn = !0), 0
            }, dj = {}.hasOwnProperty, dL = [], dD = dL.pop, dp = dL.push, dd = dL.push, dy = dL.slice,
            dB = function (a, d) {
                for (var b = 0, c = a.length; b < c; b++) {
                    if (a[b] === d) {
                        return b
                    }
                }
                return -1
            },
            dG = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped",
            ds = "[\\x20\\t\\r\\n\\f]", dh = "(?:\\\\[\\da-fA-F]{1,6}" + ds + "?|\\\\[^\\r\\n\\f]|[\\w-]|[^\0-\\x7f])+",
            dW = "\\[" + ds + "*(" + dh + ")(?:" + ds + "*([*^$|!~]?=)" + ds + "*(?:'((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\"|(" + dh + "))|)" + ds + "*\\]",
            c7 = ":(" + dh + ")(?:\\((('((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\")|((?:\\\\.|[^\\\\()[\\]]|" + dW + ")*)|.*)\\)|)",
            cT = new RegExp(ds + "+", "g"), at = new RegExp("^" + ds + "+|((?:^|[^\\\\])(?:\\\\.)*)" + ds + "+$", "g"),
            bt = new RegExp("^" + ds + "*," + ds + "*"), d4 = new RegExp("^" + ds + "*([>+~]|" + ds + ")" + ds + "*"),
            dQ = new RegExp(ds + "|>"), dZ = new RegExp(c7), dT = new RegExp("^" + dh + "$"), da = {
                ID: new RegExp("^#(" + dh + ")"),
                CLASS: new RegExp("^\\.(" + dh + ")"),
                TAG: new RegExp("^(" + dh + "|[*])"),
                ATTR: new RegExp("^" + dW),
                PSEUDO: new RegExp("^" + c7),
                CHILD: new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + ds + "*(even|odd|(([+-]|)(\\d*)n|)" + ds + "*(?:([+-]|)" + ds + "*(\\d+)|))" + ds + "*\\)|)", "i"),
                bool: new RegExp("^(?:" + dG + ")$", "i"),
                needsContext: new RegExp("^" + ds + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + ds + "*((?:-\\d)?\\d*)" + ds + "*\\)|)(?=[^-]|$)", "i")
            }, d2 = /HTML$/i, dE = /^(?:input|select|textarea|button)$/i, dk = /^h\d$/i, dm = /^[^{]+\{\s*\[native \w/,
            d5 = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/, c4 = /[+~]/,
            dN = new RegExp("\\\\[\\da-fA-F]{1,6}" + ds + "?|\\\\([^\\r\\n\\f])", "g"), dw = function (a, c) {
                var b = "0x" + a.slice(1) - 65536;
                return c || (b < 0 ? String.fromCharCode(b + 65536) : String.fromCharCode(b >> 10 | 55296, 1023 & b | 56320))
            }, dH = /([\0-\x1f\x7f]|^-?\d)|^-$|[^\0-\x1f\x7f-\uFFFF\w-]/g, di = function (a, b) {
                return b ? "\0" === a ? "\ufffd" : a.slice(0, -1) + "\\" + a.charCodeAt(a.length - 1).toString(16) + " " : "\\" + a
            }, dz = function () {
                dM()
            }, cR = cU(function (a) {
                return !0 === a.disabled && "fieldset" === a.nodeName.toLowerCase()
            }, {dir: "parentNode", next: "legend"});
        try {
            dd.apply(dL = dy.call(dA.childNodes), dA.childNodes), dL[dA.childNodes.length].nodeType
        } catch (c2) {
            dd = {
                apply: dL.length ? function (a, b) {
                    dp.apply(a, dy.call(b))
                } : function (a, d) {
                    var b = a.length, c = 0;
                    while (a[b++] = d[c++]) {
                    }
                    a.length = b - 1
                }
            }
        }

        function dK(y, g, m, w) {
            var j, q, b, x, z, k, d, h = g && g.ownerDocument, v = g ? g.nodeType : 9;
            if (m = m || [], "string" != typeof y || !y || 1 !== v && 9 !== v && 11 !== v) {
                return m
            }
            if (!w && (dM(g), g = g || cW, c3)) {
                if (11 !== v && (z = d5.exec(y))) {
                    if (j = z[1]) {
                        if (9 === v) {
                            if (!(b = g.getElementById(j))) {
                                return m
                            }
                            if (b.id === j) {
                                return m.push(b), m
                            }
                        } else {
                            if (h && (b = h.getElementById(j)) && d1(g, b) && b.id === j) {
                                return m.push(b), m
                            }
                        }
                    } else {
                        if (z[2]) {
                            return dd.apply(m, g.getElementsByTagName(y)), m
                        }
                        if ((j = z[3]) && cZ.getElementsByClassName && g.getElementsByClassName) {
                            return dd.apply(m, g.getElementsByClassName(j)), m
                        }
                    }
                }
                if (cZ.qsa && !dv[y + " "] && (!dS || !dS.test(y)) && (1 !== v || "object" !== g.nodeName.toLowerCase())) {
                    if (d = y, h = g, 1 === v && (dQ.test(y) || d4.test(y))) {
                        (h = c4.test(y) && d3(g.parentNode) || g) === g && cZ.scope || ((x = g.getAttribute("id")) ? x = x.replace(dH, di) : g.setAttribute("id", x = dJ)), q = (k = dc(y)).length;
                        while (q--) {
                            k[q] = (x ? "#" + x : ":scope") + " " + d0(k[q])
                        }
                        d = k.join(",")
                    }
                    try {
                        return dd.apply(m, h.querySelectorAll(d)), m
                    } catch (g) {
                        dv(y, !0)
                    } finally {
                        x === dJ && g.removeAttribute("id")
                    }
                }
            }
            return c9(y.replace(at, "$1"), g, m, w)
        }

        function dR() {
            var b = [];
            return function a(d, c) {
                return b.push(d + " ") > cS.cacheLength && delete a[b.shift()], a[d + " "] = c
            }
        }

        function dq(a) {
            return a[dJ] = !0, a
        }

        function cX(a) {
            var b = cW.createElement("fieldset");
            try {
                return !!a(b)
            } catch (a) {
                return !1
            } finally {
                b.parentNode && b.parentNode.removeChild(b), b = null
            }
        }

        function c8(a, d) {
            var b = a.split("|"), c = b.length;
            while (c--) {
                cS.attrHandle[b[c]] = d
            }
        }

        function dC(a, d) {
            var b = d && a, c = b && 1 === a.nodeType && 1 === d.nodeType && a.sourceIndex - d.sourceIndex;
            if (c) {
                return c
            }
            if (b) {
                while (b = b.nextSibling) {
                    if (b === d) {
                        return -1
                    }
                }
            }
            return a ? 1 : -1
        }

        function c1(a) {
            return function (b) {
                return "input" === b.nodeName.toLowerCase() && b.type === a
            }
        }

        function df(a) {
            return function (b) {
                var c = b.nodeName.toLowerCase();
                return ("input" === c || "button" === c) && b.type === a
            }
        }

        function db(a) {
            return function (b) {
                return "form" in b ? b.parentNode && !1 === b.disabled ? "label" in b ? "label" in b.parentNode ? b.parentNode.disabled === a : b.disabled === a : b.isDisabled === a || b.isDisabled !== !a && cR(b) === a : b.disabled === a : "label" in b && b.disabled === a
            }
        }

        function dU(b) {
            return dq(function (a) {
                return a = +a, dq(function (c, h) {
                    var f, g = b([], c.length, a), d = g.length;
                    while (d--) {
                        c[f = g[d]] && (c[f] = !(h[f] = c[f]))
                    }
                })
            })
        }

        function d3(a) {
            return a && "undefined" != typeof a.getElementsByTagName && a
        }

        for (c2 in cZ = dK.support = {}, dg = dK.isXML = function (a) {
            var c = a.namespaceURI, b = (a.ownerDocument || a).documentElement;
            return !d2.test(c || b && b.nodeName || "HTML")
        }, dM = dK.setDocument = function (a) {
            var d, b, c = a ? a.ownerDocument || a : dA;
            return c != cW && 9 === c.nodeType && c.documentElement && (ct = (cW = c).documentElement, c3 = !dg(cW), dA != cW && (b = cW.defaultView) && b.top !== b && (b.addEventListener ? b.addEventListener("unload", dz, !1) : b.attachEvent && b.attachEvent("onunload", dz)), cZ.scope = cX(function (f) {
                return ct.appendChild(f).appendChild(cW.createElement("div")), "undefined" != typeof f.querySelectorAll && !f.querySelectorAll(":scope fieldset div").length
            }), cZ.attributes = cX(function (f) {
                return f.className = "i", !f.getAttribute("className")
            }), cZ.getElementsByTagName = cX(function (f) {
                return f.appendChild(cW.createComment("")), !f.getElementsByTagName("*").length
            }), cZ.getElementsByClassName = dm.test(cW.getElementsByClassName), cZ.getById = cX(function (f) {
                return ct.appendChild(f).id = dJ, !cW.getElementsByName || !cW.getElementsByName(dJ).length
            }), cZ.getById ? (cS.filter.ID = function (f) {
                var g = f.replace(dN, dw);
                return function (h) {
                    return h.getAttribute("id") === g
                }
            }, cS.find.ID = function (f, h) {
                if ("undefined" != typeof h.getElementById && c3) {
                    var g = h.getElementById(f);
                    return g ? [g] : []
                }
            }) : (cS.filter.ID = function (f) {
                var g = f.replace(dN, dw);
                return function (h) {
                    var i = "undefined" != typeof h.getAttributeNode && h.getAttributeNode("id");
                    return i && i.value === g
                }
            }, cS.find.ID = function (f, l) {
                if ("undefined" != typeof l.getElementById && c3) {
                    var h, k, g, j = l.getElementById(f);
                    if (j) {
                        if ((h = j.getAttributeNode("id")) && h.value === f) {
                            return [j]
                        }
                        g = l.getElementsByName(f), k = 0;
                        while (j = g[k++]) {
                            if ((h = j.getAttributeNode("id")) && h.value === f) {
                                return [j]
                            }
                        }
                    }
                    return []
                }
            }), cS.find.TAG = cZ.getElementsByTagName ? function (f, g) {
                return "undefined" != typeof g.getElementsByTagName ? g.getElementsByTagName(f) : cZ.qsa ? g.querySelectorAll(f) : void 0
            } : function (f, l) {
                var h, k = [], g = 0, j = l.getElementsByTagName(f);
                if ("*" === f) {
                    while (h = j[g++]) {
                        1 === h.nodeType && k.push(h)
                    }
                    return k
                }
                return j
            }, cS.find.CLASS = cZ.getElementsByClassName && function (f, g) {
                if ("undefined" != typeof g.getElementsByClassName && c3) {
                    return g.getElementsByClassName(f)
                }
            }, dI = [], dS = [], (cZ.qsa = dm.test(cW.querySelectorAll)) && (cX(function (f) {
                var g;
                ct.appendChild(f).innerHTML = "<a id='" + dJ + "'></a><select id='" + dJ + "-\r\\' msallowcapture=''><option selected=''></option></select>", f.querySelectorAll("[msallowcapture^='']").length && dS.push("[*^$]=" + ds + "*(?:''|\"\")"), f.querySelectorAll("[selected]").length || dS.push("\\[" + ds + "*(?:value|" + dG + ")"), f.querySelectorAll("[id~=" + dJ + "-]").length || dS.push("~="), (g = cW.createElement("input")).setAttribute("name", ""), f.appendChild(g), f.querySelectorAll("[name='']").length || dS.push("\\[" + ds + "*name" + ds + "*=" + ds + "*(?:''|\"\")"), f.querySelectorAll(":checked").length || dS.push(":checked"), f.querySelectorAll("a#" + dJ + "+*").length || dS.push(".#.+[+~]"), f.querySelectorAll("\\\f"), dS.push("[\\r\\n\\f]")
            }), cX(function (f) {
                f.innerHTML = "<a href='' disabled='disabled'></a><select disabled='disabled'><option/></select>";
                var g = cW.createElement("input");
                g.setAttribute("type", "hidden"), f.appendChild(g).setAttribute("name", "D"), f.querySelectorAll("[name=d]").length && dS.push("name" + ds + "*[*^$|!~]?="), 2 !== f.querySelectorAll(":enabled").length && dS.push(":enabled", ":disabled"), ct.appendChild(f).disabled = !0, 2 !== f.querySelectorAll(":disabled").length && dS.push(":enabled", ":disabled"), f.querySelectorAll("*,:x"), dS.push(",.*:")
            })), (cZ.matchesSelector = dm.test(cV = ct.matches || ct.webkitMatchesSelector || ct.mozMatchesSelector || ct.oMatchesSelector || ct.msMatchesSelector)) && cX(function (f) {
                cZ.disconnectedMatch = cV.call(f, "*"), cV.call(f, "[s!='']:x"), dI.push("!=", c7)
            }), dS = dS.length && new RegExp(dS.join("|")), dI = dI.length && new RegExp(dI.join("|")), d = dm.test(ct.compareDocumentPosition), d1 = d || dm.test(ct.contains) ? function (f, i) {
                var g = 9 === f.nodeType ? f.documentElement : f, h = i && i.parentNode;
                return f === h || !(!h || 1 !== h.nodeType || !(g.contains ? g.contains(h) : f.compareDocumentPosition && 16 & f.compareDocumentPosition(h)))
            } : function (f, g) {
                if (g) {
                    while (g = g.parentNode) {
                        if (g === f) {
                            return !0
                        }
                    }
                }
                return !1
            }, c0 = d ? function (f, h) {
                if (f === h) {
                    return dn = !0, 0
                }
                var g = !f.compareDocumentPosition - !h.compareDocumentPosition;
                return g || (1 & (g = (f.ownerDocument || f) == (h.ownerDocument || h) ? f.compareDocumentPosition(h) : 1) || !cZ.sortDetached && h.compareDocumentPosition(f) === g ? f == cW || f.ownerDocument == dA && d1(dA, f) ? -1 : h == cW || h.ownerDocument == dA && d1(dA, h) ? 1 : dP ? dB(dP, f) - dB(dP, h) : 0 : 4 & g ? -1 : 1)
            } : function (g, p) {
                if (g === p) {
                    return dn = !0, 0
                }
                var j, l = 0, h = g.parentNode, k = p.parentNode, f = [g], m = [p];
                if (!h || !k) {
                    return g == cW ? -1 : p == cW ? 1 : h ? -1 : k ? 1 : dP ? dB(dP, g) - dB(dP, p) : 0
                }
                if (h === k) {
                    return dC(g, p)
                }
                j = g;
                while (j = j.parentNode) {
                    f.unshift(j)
                }
                j = p;
                while (j = j.parentNode) {
                    m.unshift(j)
                }
                while (f[l] === m[l]) {
                    l++
                }
                return l ? dC(f[l], m[l]) : f[l] == dA ? -1 : m[l] == dA ? 1 : 0
            }), cW
        }, dK.matches = function (a, b) {
            return dK(a, null, null, b)
        }, dK.matchesSelector = function (a, c) {
            if (dM(a), cZ.matchesSelector && c3 && !dv[c + " "] && (!dI || !dI.test(c)) && (!dS || !dS.test(c))) {
                try {
                    var b = cV.call(a, c);
                    if (b || cZ.disconnectedMatch || a.document && 11 !== a.document.nodeType) {
                        return b
                    }
                } catch (a) {
                    dv(c, !0)
                }
            }
            return 0 < dK(c, cW, null, [a]).length
        }, dK.contains = function (a, b) {
            return (a.ownerDocument || a) != cW && dM(a), d1(a, b)
        }, dK.attr = function (a, d) {
            (a.ownerDocument || a) != cW && dM(a);
            var b = cS.attrHandle[d.toLowerCase()],
                c = b && dj.call(cS.attrHandle, d.toLowerCase()) ? b(a, d, !c3) : void 0;
            return void 0 !== c ? c : cZ.attributes || !c3 ? a.getAttribute(d) : (c = a.getAttributeNode(d)) && c.specified ? c.value : null
        }, dK.escape = function (a) {
            return (a + "").replace(dH, di)
        }, dK.error = function (a) {
            throw new Error("Syntax error, unrecognized expression: " + a)
        }, dK.uniqueSort = function (a) {
            var f, c = [], d = 0, b = 0;
            if (dn = !cZ.detectDuplicates, dP = !cZ.sortStable && a.slice(0), a.sort(c0), dn) {
                while (f = a[b++]) {
                    f === a[b] && (d = c.push(b))
                }
                while (d--) {
                    a.splice(c[d], 1)
                }
            }
            return dP = null, a
        }, dx = dK.getText = function (a) {
            var f, c = "", d = 0, b = a.nodeType;
            if (b) {
                if (1 === b || 9 === b || 11 === b) {
                    if ("string" == typeof a.textContent) {
                        return a.textContent
                    }
                    for (a = a.firstChild; a; a = a.nextSibling) {
                        c += dx(a)
                    }
                } else {
                    if (3 === b || 4 === b) {
                        return a.nodeValue
                    }
                }
            } else {
                while (f = a[d++]) {
                    c += dx(f)
                }
            }
            return c
        }, (cS = dK.selectors = {
            cacheLength: 50,
            createPseudo: dq,
            match: da,
            attrHandle: {},
            find: {},
            relative: {
                ">": {dir: "parentNode", first: !0},
                " ": {dir: "parentNode"},
                "+": {dir: "previousSibling", first: !0},
                "~": {dir: "previousSibling"}
            },
            preFilter: {
                ATTR: function (a) {
                    return a[1] = a[1].replace(dN, dw), a[3] = (a[3] || a[4] || a[5] || "").replace(dN, dw), "~=" === a[2] && (a[3] = " " + a[3] + " "), a.slice(0, 4)
                }, CHILD: function (a) {
                    return a[1] = a[1].toLowerCase(), "nth" === a[1].slice(0, 3) ? (a[3] || dK.error(a[0]), a[4] = +(a[4] ? a[5] + (a[6] || 1) : 2 * ("even" === a[3] || "odd" === a[3])), a[5] = +(a[7] + a[8] || "odd" === a[3])) : a[3] && dK.error(a[0]), a
                }, PSEUDO: function (a) {
                    var c, b = !a[6] && a[2];
                    return da.CHILD.test(a[0]) ? null : (a[3] ? a[2] = a[4] || a[5] || "" : b && dZ.test(b) && (c = dc(b, !0)) && (c = b.indexOf(")", b.length - c) - b.length) && (a[0] = a[0].slice(0, c), a[2] = b.slice(0, c)), a.slice(0, 3))
                }
            },
            filter: {
                TAG: function (a) {
                    var b = a.replace(dN, dw).toLowerCase();
                    return "*" === a ? function () {
                        return !0
                    } : function (c) {
                        return c.nodeName && c.nodeName.toLowerCase() === b
                    }
                }, CLASS: function (a) {
                    var b = dr[a + " "];
                    return b || (b = new RegExp("(^|" + ds + ")" + a + "(" + ds + "|$)")) && dr(a, function (c) {
                        return b.test("string" == typeof c.className && c.className || "undefined" != typeof c.getAttribute && c.getAttribute("class") || "")
                    })
                }, ATTR: function (b, c, a) {
                    return function (d) {
                        var f = dK.attr(d, b);
                        return null == f ? "!=" === c : !c || (f += "", "=" === c ? f === a : "!=" === c ? f !== a : "^=" === c ? a && 0 === f.indexOf(a) : "*=" === c ? a && -1 < f.indexOf(a) : "$=" === c ? a && f.slice(-a.length) === a : "~=" === c ? -1 < (" " + f.replace(cT, " ") + " ").indexOf(a) : "|=" === c && (f === a || f.slice(0, a.length + 1) === a + "-"))
                    }
                }, CHILD: function (c, a, f, b, i) {
                    var k = "nth" !== c.slice(0, 3), d = "last" !== c.slice(-4), j = "of-type" === a;
                    return 1 === b && 0 === i ? function (g) {
                        return !!g.parentNode
                    } : function (q, D, y) {
                        var B, w, z, g, C, E, x = k !== d ? "nextSibling" : "previousSibling", h = q.parentNode,
                            v = j && q.nodeName.toLowerCase(), A = !y && !j, m = !1;
                        if (h) {
                            if (k) {
                                while (x) {
                                    g = q;
                                    while (g = g[x]) {
                                        if (j ? g.nodeName.toLowerCase() === v : 1 === g.nodeType) {
                                            return !1
                                        }
                                    }
                                    E = x = "only" === c && !E && "nextSibling"
                                }
                                return !0
                            }
                            if (E = [d ? h.firstChild : h.lastChild], d && A) {
                                m = (C = (B = (w = (z = (g = h)[dJ] || (g[dJ] = {}))[g.uniqueID] || (z[g.uniqueID] = {}))[c] || [])[0] === dl && B[1]) && B[2], g = C && h.childNodes[C];
                                while (g = ++C && g && g[x] || (m = C = 0) || E.pop()) {
                                    if (1 === g.nodeType && ++m && g === q) {
                                        w[c] = [dl, C, m];
                                        break
                                    }
                                }
                            } else {
                                if (A && (m = C = (B = (w = (z = (g = q)[dJ] || (g[dJ] = {}))[g.uniqueID] || (z[g.uniqueID] = {}))[c] || [])[0] === dl && B[1]), !1 === m) {
                                    while (g = ++C && g && g[x] || (m = C = 0) || E.pop()) {
                                        if ((j ? g.nodeName.toLowerCase() === v : 1 === g.nodeType) && ++m && (A && ((w = (z = g[dJ] || (g[dJ] = {}))[g.uniqueID] || (z[g.uniqueID] = {}))[c] = [dl, m]), g === q)) {
                                            break
                                        }
                                    }
                                }
                            }
                            return (m -= i) === b || m % b == 0 && 0 <= m / b
                        }
                    }
                }, PSEUDO: function (c, d) {
                    var f, b = cS.pseudos[c] || cS.setFilters[c.toLowerCase()] || dK.error("unsupported pseudo: " + c);
                    return b[dJ] ? b(d) : 1 < b.length ? (f = [c, c, "", d], cS.setFilters.hasOwnProperty(c.toLowerCase()) ? dq(function (a, k) {
                        var h, j = b(a, d), g = j.length;
                        while (g--) {
                            a[h = dB(a, j[g])] = !(k[h] = j[g])
                        }
                    }) : function (a) {
                        return b(a, 0, f)
                    }) : b
                }
            },
            pseudos: {
                not: dq(function (a) {
                    var c = [], b = [], d = c6(a.replace(at, "$1"));
                    return d[dJ] ? dq(function (g, m, j, l) {
                        var h, k = d(g, null, l, []), f = g.length;
                        while (f--) {
                            (h = k[f]) && (g[f] = !(m[f] = h))
                        }
                    }) : function (f, h, g) {
                        return c[0] = f, d(c, null, g, b), c[0] = null, !b.pop()
                    }
                }), has: dq(function (a) {
                    return function (b) {
                        return 0 < dK(a, b).length
                    }
                }), contains: dq(function (a) {
                    return a = a.replace(dN, dw), function (b) {
                        return -1 < (b.textContent || dx(b)).indexOf(a)
                    }
                }), lang: dq(function (a) {
                    return dT.test(a || "") || dK.error("unsupported lang: " + a), a = a.replace(dN, dw).toLowerCase(), function (b) {
                        var c;
                        do {
                            if (c = c3 ? b.lang : b.getAttribute("xml:lang") || b.getAttribute("lang")) {
                                return (c = c.toLowerCase()) === a || 0 === c.indexOf(a + "-")
                            }
                        } while ((b = b.parentNode) && 1 === b.nodeType);
                        return !1
                    }
                }), target: function (a) {
                    var b = du.location && du.location.hash;
                    return b && b.slice(1) === a.id
                }, root: function (a) {
                    return a === ct
                }, focus: function (a) {
                    return a === cW.activeElement && (!cW.hasFocus || cW.hasFocus()) && !!(a.type || a.href || ~a.tabIndex)
                }, enabled: db(!1), disabled: db(!0), checked: function (a) {
                    var b = a.nodeName.toLowerCase();
                    return "input" === b && !!a.checked || "option" === b && !!a.selected
                }, selected: function (a) {
                    return a.parentNode && a.parentNode.selectedIndex, !0 === a.selected
                }, empty: function (a) {
                    for (a = a.firstChild; a; a = a.nextSibling) {
                        if (a.nodeType < 6) {
                            return !1
                        }
                    }
                    return !0
                }, parent: function (a) {
                    return !cS.pseudos.empty(a)
                }, header: function (a) {
                    return dk.test(a.nodeName)
                }, input: function (a) {
                    return dE.test(a.nodeName)
                }, button: function (a) {
                    var b = a.nodeName.toLowerCase();
                    return "input" === b && "button" === a.type || "button" === b
                }, text: function (a) {
                    var b;
                    return "input" === a.nodeName.toLowerCase() && "text" === a.type && (null == (b = a.getAttribute("type")) || "text" === b.toLowerCase())
                }, first: dU(function () {
                    return [0]
                }), last: dU(function (a, b) {
                    return [b - 1]
                }), eq: dU(function (a, c, b) {
                    return [b < 0 ? b + c : b]
                }), even: dU(function (a, c) {
                    for (var b = 0; b < c; b += 2) {
                        a.push(b)
                    }
                    return a
                }), odd: dU(function (a, c) {
                    for (var b = 1; b < c; b += 2) {
                        a.push(b)
                    }
                    return a
                }), lt: dU(function (a, d, b) {
                    for (var c = b < 0 ? b + d : d < b ? d : b; 0 <= --c;) {
                        a.push(c)
                    }
                    return a
                }), gt: dU(function (a, d, b) {
                    for (var c = b < 0 ? b + d : b; ++c < d;) {
                        a.push(c)
                    }
                    return a
                })
            }
        }).pseudos.nth = cS.pseudos.eq, {radio: !0, checkbox: !0, file: !0, password: !0, image: !0}) {
            cS.pseudos[c2] = c1(c2)
        }
        for (c2 in {submit: !0, reset: !0}) {
            cS.pseudos[c2] = df(c2)
        }

        function dt() {
        }

        function d0(a) {
            for (var d = 0, b = a.length, c = ""; d < b; d++) {
                c += a[d].value
            }
            return c
        }

        function cU(i, b, j) {
            var k = b.dir, g = b.next, a = g || k, d = j && "parentNode" === a, h = dF++;
            return b.first ? function (c, l, f) {
                while (c = c[k]) {
                    if (1 === c.nodeType || d) {
                        return i(c, l, f)
                    }
                }
                return !1
            } : function (f, s, m) {
                var q, l, p, c = [dl, h];
                if (m) {
                    while (f = f[k]) {
                        if ((1 === f.nodeType || d) && i(f, s, m)) {
                            return !0
                        }
                    }
                } else {
                    while (f = f[k]) {
                        if (1 === f.nodeType || d) {
                            if (l = (p = f[dJ] || (f[dJ] = {}))[f.uniqueID] || (p[f.uniqueID] = {}), g && g === f.nodeName.toLowerCase()) {
                                f = f[k] || f
                            } else {
                                if ((q = l[a]) && q[0] === dl && q[1] === h) {
                                    return c[2] = q[2]
                                }
                                if ((l[a] = c)[2] = i(f, s, m)) {
                                    return !0
                                }
                            }
                        }
                    }
                }
                return !1
            }
        }

        function dX(a) {
            return 1 < a.length ? function (b, f, c) {
                var d = a.length;
                while (d--) {
                    if (!a[d](b, f, c)) {
                        return !1
                    }
                }
                return !0
            } : a[0]
        }

        function dO(c, m, g, j, d) {
            for (var h, b = [], k = 0, p = c.length, f = null != m; k < p; k++) {
                (h = c[k]) && (g && !g(h, j, d) || (b.push(h), f && m.push(k)))
            }
            return b
        }

        function cY(a, f, c, i, j, b) {
            return i && !i[dJ] && (i = cY(i)), j && !j[dJ] && (j = cY(j, b)), dq(function (h, A, v, y) {
                var m, w, d, z = [], B = [], q = A.length, g = h || function (l, u, p) {
                        for (var s = 0, o = u.length; s < o; s++) {
                            dK(l, u[s], p)
                        }
                        return p
                    }(f || "*", v.nodeType ? [v] : v, []), k = !a || !h && f ? g : dO(g, z, a, v, y),
                    x = c ? j || (h ? a : q || i) ? [] : A : k;
                if (c && c(k, x, v, y), i) {
                    m = dO(x, B), i(m, [], v, y), w = m.length;
                    while (w--) {
                        (d = m[w]) && (x[B[w]] = !(k[B[w]] = d))
                    }
                }
                if (h) {
                    if (j || a) {
                        if (j) {
                            m = [], w = x.length;
                            while (w--) {
                                (d = x[w]) && m.push(k[w] = d)
                            }
                            j(null, x = [], m, y)
                        }
                        w = x.length;
                        while (w--) {
                            (d = x[w]) && -1 < (m = j ? dB(h, d) : z[w]) && (h[m] = !(A[m] = d))
                        }
                    }
                } else {
                    x = dO(x === A ? x.splice(q, x.length) : x), j ? j(null, A, x, y) : dd.apply(A, x)
                }
            })
        }

        function c5(f) {
            for (var g, q, j, m = f.length, k = cS.relative[f[0].type], b = k || cS.relative[" "], p = k ? 1 : 0, v = cU(function (a) {
                return a === g
            }, b, !0), h = cU(function (a) {
                return -1 < dB(g, a)
            }, b, !0), d = [function (a, l, c) {
                var i = !k && (c || l !== dV) || ((g = l).nodeType ? v(a, l, c) : h(a, l, c));
                return g = null, i
            }]; p < m; p++) {
                if (q = cS.relative[f[p].type]) {
                    d = [cU(dX(d), q)]
                } else {
                    if ((q = cS.filter[f[p].type].apply(null, f[p].matches))[dJ]) {
                        for (j = ++p; j < m; j++) {
                            if (cS.relative[f[j].type]) {
                                break
                            }
                        }
                        return cY(1 < p && dX(d), 1 < p && d0(f.slice(0, p - 1).concat({value: " " === f[p - 2].type ? "*" : ""})).replace(at, "$1"), q, p < j && c5(f.slice(p, j)), j < m && c5(f = f.slice(j)), j < m && d0(f))
                    }
                    d.push(q)
                }
            }
            return dX(d)
        }

        return dt.prototype = cS.filters = cS.pseudos, cS.setFilters = new dt, dc = dK.tokenize = function (c, m) {
            var g, j, d, h, b, k, p, f = dY[c + " "];
            if (f) {
                return m ? 0 : f.slice(0)
            }
            b = c, k = [], p = cS.preFilter;
            while (b) {
                for (h in g && !(j = bt.exec(b)) || (j && (b = b.slice(j[0].length) || b), k.push(d = [])), g = !1, (j = d4.exec(b)) && (g = j.shift(), d.push({
                    value: g,
                    type: j[0].replace(at, " ")
                }), b = b.slice(g.length)), cS.filter) {
                    !(j = da[h].exec(b)) || p[h] && !(j = p[h](j)) || (g = j.shift(), d.push({
                        value: g,
                        type: h,
                        matches: j
                    }), b = b.slice(g.length))
                }
                if (!g) {
                    break
                }
            }
            return m ? b.length : b ? dK.error(c) : dY(c, k).slice(0)
        }, c6 = dK.compile = function (c, k) {
            var g, l, q, f, p, j, d = [], h = [], b = cQ[c + " "];
            if (!b) {
                k || (k = dc(c)), g = k.length;
                while (g--) {
                    (b = c5(k[g]))[dJ] ? d.push(b) : h.push(b)
                }
                (b = cQ(c, (l = h, f = 0 < (q = d).length, p = 0 < l.length, j = function (x, I, D, G, B) {
                    var E, m, H, J = 0, C = "0", v = x && [], y = [], F = dV, w = x || p && cS.find.TAG("*", B),
                        A = dl += null == F ? 1 : Math.random() || 0.1, z = w.length;
                    for (B && (dV = I == cW || I || B); C !== z && null != (E = w[C]); C++) {
                        if (p && E) {
                            m = 0, I || E.ownerDocument == cW || (dM(E), D = !c3);
                            while (H = l[m++]) {
                                if (H(E, I || cW, D)) {
                                    G.push(E);
                                    break
                                }
                            }
                            B && (dl = A)
                        }
                        f && ((E = !H && E) && J--, x && v.push(E))
                    }
                    if (J += C, f && C !== J) {
                        m = 0;
                        while (H = q[m++]) {
                            H(v, y, I, D)
                        }
                        if (x) {
                            if (0 < J) {
                                while (C--) {
                                    v[C] || y[C] || (y[C] = dD.call(G))
                                }
                            }
                            y = dO(y)
                        }
                        dd.apply(G, y), B && !x && 0 < y.length && 1 < J + q.length && dK.uniqueSort(G)
                    }
                    return B && (dl = A, dV = F), v
                }, f ? dq(j) : j))).selector = c
            }
            return b
        }, c9 = dK.select = function (f, q, j, m) {
            var g, k, b, p, v, h = "function" == typeof f && f, d = !m && dc(f = h.selector || f);
            if (j = j || [], 1 === d.length) {
                if (2 < (k = d[0] = d[0].slice(0)).length && "ID" === (b = k[0]).type && 9 === q.nodeType && c3 && cS.relative[k[1].type]) {
                    if (!(q = (cS.find.ID(b.matches[0].replace(dN, dw), q) || [])[0])) {
                        return j
                    }
                    h && (q = q.parentNode), f = f.slice(k.shift().value.length)
                }
                g = da.needsContext.test(f) ? 0 : k.length;
                while (g--) {
                    if (b = k[g], cS.relative[p = b.type]) {
                        break
                    }
                    if ((v = cS.find[p]) && (m = v(b.matches[0].replace(dN, dw), c4.test(k[0].type) && d3(q.parentNode) || q))) {
                        if (k.splice(g, 1), !(f = m.length && d0(k))) {
                            return dd.apply(j, m), j
                        }
                        break
                    }
                }
            }
            return (h || c6(f, d))(m, q, !c3, j, !q || c4.test(f) && d3(q.parentNode) || q), j
        }, cZ.sortStable = dJ.split("").sort(c0).join("") === dJ, cZ.detectDuplicates = !!dn, dM(), cZ.sortDetached = cX(function (a) {
            return 1 & a.compareDocumentPosition(cW.createElement("fieldset"))
        }), cX(function (a) {
            return a.innerHTML = "<a href='#'></a>", "#" === a.firstChild.getAttribute("href")
        }) || c8("type|href|height|width", function (a, c, b) {
            if (!b) {
                return a.getAttribute(c, "type" === c.toLowerCase() ? 1 : 2)
            }
        }), cZ.attributes && cX(function (a) {
            return a.innerHTML = "<input/>", a.firstChild.setAttribute("value", ""), "" === a.firstChild.getAttribute("value")
        }) || c8("value", function (a, c, b) {
            if (!b && "input" === a.nodeName.toLowerCase()) {
                return a.defaultValue
            }
        }), cX(function (a) {
            return null == a.getAttribute("disabled")
        }) || c8(dG, function (a, d, b) {
            var c;
            if (!b) {
                return !0 === a[d] ? d.toLowerCase() : (c = a.getAttributeNode(d)) && c.specified ? c.value : null
            }
        }), dK
    }(aw);
    b4.find = aB, b4.expr = aB.selectors, b4.expr[":"] = b4.expr.pseudos, b4.uniqueSort = b4.unique = aB.uniqueSort, b4.text = aB.getText, b4.isXMLDoc = aB.isXML, b4.contains = aB.contains, b4.escapeSelector = aB.escape;
    var aZ = function (a, f, c) {
        var d = [], b = void 0 !== c;
        while ((a = a[f]) && 9 !== a.nodeType) {
            if (1 === a.nodeType) {
                if (b && b4(a).is(c)) {
                    break
                }
                d.push(a)
            }
        }
        return d
    }, ca = function (a, c) {
        for (var b = []; a; a = a.nextSibling) {
            1 === a.nodeType && a !== c && b.push(a)
        }
        return b
    }, bh = b4.expr.match.needsContext;

    function ai(a, b) {
        return a.nodeName && a.nodeName.toLowerCase() === b.toLowerCase()
    }

    var bA = /^<([a-z][^\/\0>:\x20\t\r\n\f]*)[\x20\t\r\n\f]*\/?>(?:<\/\1>|)$/i;

    function aC(a, b, c) {
        return bs(b) ? b4.grep(a, function (d, f) {
            return !!b.call(d, f, d) !== c
        }) : b.nodeType ? b4.grep(a, function (d) {
            return d === b !== c
        }) : "string" != typeof b ? b4.grep(a, function (d) {
            return -1 < a5.call(b, d) !== c
        }) : b4.filter(b, a, c)
    }

    b4.filter = function (a, d, b) {
        var c = d[0];
        return b && (a = ":not(" + a + ")"), 1 === d.length && 1 === c.nodeType ? b4.find.matchesSelector(c, a) ? [c] : [] : b4.find.matches(a, b4.grep(d, function (f) {
            return 1 === f.nodeType
        }))
    }, b4.fn.extend({
        find: function (a) {
            var f, c, d = this.length, b = this;
            if ("string" != typeof a) {
                return this.pushStack(b4(a).filter(function () {
                    for (f = 0; f < d; f++) {
                        if (b4.contains(b[f], this)) {
                            return !0
                        }
                    }
                }))
            }
            for (c = this.pushStack([]), f = 0; f < d; f++) {
                b4.find(a, b[f], c)
            }
            return 1 < d ? b4.uniqueSort(c) : c
        }, filter: function (a) {
            return this.pushStack(aC(this, a || [], !1))
        }, not: function (a) {
            return this.pushStack(aC(this, a || [], !0))
        }, is: function (a) {
            return !!aC(this, "string" == typeof a && bh.test(a) ? b4(a) : a || [], !1).length
        }
    });
    var bb, bR = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]+))$/;
    (b4.fn.init = function (a, f, c) {
        var d, b;
        if (!a) {
            return this
        }
        if (c = c || bb, "string" == typeof a) {
            if (!(d = "<" === a[0] && ">" === a[a.length - 1] && 3 <= a.length ? [null, a, null] : bR.exec(a)) || !d[1] && f) {
                return !f || f.jquery ? (f || c).find(a) : this.constructor(f).find(a)
            }
            if (d[1]) {
                if (f = f instanceof b4 ? f[0] : f, b4.merge(this, b4.parseHTML(d[1], f && f.nodeType ? f.ownerDocument || f : aI, !0)), bA.test(d[1]) && b4.isPlainObject(f)) {
                    for (d in f) {
                        bs(this[d]) ? this[d](f[d]) : this.attr(d, f[d])
                    }
                }
                return this
            }
            return (b = aI.getElementById(d[2])) && (this[0] = b, this.length = 1), this
        }
        return a.nodeType ? (this[0] = a, this.length = 1, this) : bs(a) ? void 0 !== c.ready ? c.ready(a) : a(b4) : b4.makeArray(a, this)
    }).prototype = b4.fn, bb = b4(aI);
    var bn = /^(?:parents|prev(?:Until|All))/, a0 = {children: !0, contents: !0, next: !0, prev: !0};

    function bG(a, b) {
        while ((a = a[b]) && 1 !== a.nodeType) {
        }
        return a
    }

    b4.fn.extend({
        has: function (a) {
            var c = b4(a, this), b = c.length;
            return this.filter(function () {
                for (var d = 0; d < b; d++) {
                    if (b4.contains(this, c[d])) {
                        return !0
                    }
                }
            })
        }, closest: function (c, j) {
            var f, h = 0, d = this.length, g = [], b = "string" != typeof c && b4(c);
            if (!bh.test(c)) {
                for (; h < d; h++) {
                    for (f = this[h]; f && f !== j; f = f.parentNode) {
                        if (f.nodeType < 11 && (b ? -1 < b.index(f) : 1 === f.nodeType && b4.find.matchesSelector(f, c))) {
                            g.push(f);
                            break
                        }
                    }
                }
            }
            return this.pushStack(1 < g.length ? b4.uniqueSort(g) : g)
        }, index: function (a) {
            return a ? "string" == typeof a ? a5.call(b4(a), this[0]) : a5.call(this, a.jquery ? a[0] : a) : this[0] && this[0].parentNode ? this.first().prevAll().length : -1
        }, add: function (a, b) {
            return this.pushStack(b4.uniqueSort(b4.merge(this.get(), b4(a, b))))
        }, addBack: function (a) {
            return this.add(null == a ? this.prevObject : this.prevObject.filter(a))
        }
    }), b4.each({
        parent: function (a) {
            var b = a.parentNode;
            return b && 11 !== b.nodeType ? b : null
        }, parents: function (a) {
            return aZ(a, "parentNode")
        }, parentsUntil: function (a, c, b) {
            return aZ(a, "parentNode", b)
        }, next: function (a) {
            return bG(a, "nextSibling")
        }, prev: function (a) {
            return bG(a, "previousSibling")
        }, nextAll: function (a) {
            return aZ(a, "nextSibling")
        }, prevAll: function (a) {
            return aZ(a, "previousSibling")
        }, nextUntil: function (a, c, b) {
            return aZ(a, "nextSibling", b)
        }, prevUntil: function (a, c, b) {
            return aZ(a, "previousSibling", b)
        }, siblings: function (a) {
            return ca((a.parentNode || {}).firstChild, a)
        }, children: function (a) {
            return ca(a.firstChild)
        }, contents: function (a) {
            return null != a.contentDocument && bX(a.contentDocument) ? a.contentDocument : (ai(a, "template") && (a = a.content || a), b4.merge([], a.childNodes))
        }
    }, function (b, a) {
        b4.fn[b] = function (c, f) {
            var d = b4.map(this, a, c);
            return "Until" !== b.slice(-5) && (f = c), f && "string" == typeof f && (d = b4.filter(f, d)), 1 < this.length && (a0[b] || b4.uniqueSort(d), bn.test(b) && d.reverse()), this.pushStack(d)
        }
    });
    var bM = /[^\x20\t\r\n\f]+/g;

    function bY(a) {
        return a
    }

    function bu(a) {
        throw a
    }

    function a6(a, f, c, d) {
        var b;
        try {
            a && bs(b = a.promise) ? b.call(a).done(f).fail(c) : a && bs(b = a.then) ? b.call(a, f, c) : f.apply(void 0, [a].slice(d))
        } catch (a) {
            c.apply(void 0, [a])
        }
    }

    b4.Callbacks = function (q) {
        var g, m;
        q = "string" == typeof q ? (g = q, m = {}, b4.each(g.match(bM) || [], function (a, c) {
            m[c] = !0
        }), m) : b4.extend({}, q);
        var j, w, p, b, v = [], x = [], k = -1, d = function () {
            for (b = b || q.once, p = j = !0; x.length; k = -1) {
                w = x.shift();
                while (++k < v.length) {
                    !1 === v[k].apply(w[0], w[1]) && q.stopOnFalse && (k = v.length, w = !1)
                }
            }
            q.memory || (w = !1), j = !1, b && (v = w ? [] : "")
        }, h = {
            add: function () {
                return v && (w && !j && (k = v.length - 1, x.push(w)), function a(c) {
                    b4.each(c, function (f, i) {
                        bs(i) ? q.unique && h.has(i) || v.push(i) : i && i.length && "string" !== cs(i) && a(i)
                    })
                }(arguments), w && !j && d()), this
            }, remove: function () {
                return b4.each(arguments, function (a, f) {
                    var c;
                    while (-1 < (c = b4.inArray(f, v, c))) {
                        v.splice(c, 1), c <= k && k--
                    }
                }), this
            }, has: function (a) {
                return a ? -1 < b4.inArray(a, v) : 0 < v.length
            }, empty: function () {
                return v && (v = []), this
            }, disable: function () {
                return b = x = [], v = w = "", this
            }, disabled: function () {
                return !v
            }, lock: function () {
                return b = x = [], w || j || (v = w = ""), this
            }, locked: function () {
                return !!b
            }, fireWith: function (a, c) {
                return b || (c = [a, (c = c || []).slice ? c.slice() : c], x.push(c), j || d()), this
            }, fire: function () {
                return h.fireWith(this, arguments), this
            }, fired: function () {
                return !!p
            }
        };
        return h
    }, b4.extend({
        Deferred: function (c) {
            var f = [["notify", "progress", b4.Callbacks("memory"), b4.Callbacks("memory"), 2], ["resolve", "done", b4.Callbacks("once memory"), b4.Callbacks("once memory"), 0, "resolved"], ["reject", "fail", b4.Callbacks("once memory"), b4.Callbacks("once memory"), 1, "rejected"]],
                d = "pending", b = {
                    state: function () {
                        return d
                    }, always: function () {
                        return g.done(arguments).fail(arguments), this
                    }, "catch": function (a) {
                        return b.then(null, a)
                    }, pipe: function () {
                        var a = arguments;
                        return b4.Deferred(function (e) {
                            b4.each(f, function (h, j) {
                                var i = bs(a[j[4]]) && a[j[4]];
                                g[j[1]](function () {
                                    var k = i && i.apply(this, arguments);
                                    k && bs(k.promise) ? k.promise().progress(e.notify).done(e.resolve).fail(e.reject) : e[j[0] + "With"](this, i ? [k] : arguments)
                                })
                            }), a = null
                        }).promise()
                    }, then: function (i, e, h) {
                        var j = 0;

                        function a(l, m, k, n) {
                            return function () {
                                var p = this, q = arguments, o = function () {
                                    var r, u;
                                    if (!(l < j)) {
                                        if ((r = k.apply(p, q)) === m.promise()) {
                                            throw new TypeError("Thenable self-resolution")
                                        }
                                        u = r && ("object" == typeof r || "function" == typeof r) && r.then, bs(u) ? n ? u.call(r, a(j, m, bY, n), a(j, m, bu, n)) : (j++, u.call(r, a(j, m, bY, n), a(j, m, bu, n), a(j, m, bY, m.notifyWith))) : (k !== bY && (p = void 0, q = [r]), (n || m.resolveWith)(p, q))
                                    }
                                }, s = n ? o : function () {
                                    try {
                                        r()
                                    } catch (r) {
                                        b4.Deferred.exceptionHook && b4.Deferred.exceptionHook(r, s.stackTrace), j <= l + 1 && (k !== bu && (p = void 0, q = [r]), m.rejectWith(p, q))
                                    }
                                };
                                l ? s() : (b4.Deferred.getStackHook && (s.stackTrace = b4.Deferred.getStackHook()), aw.setTimeout(s))
                            }
                        }

                        return b4.Deferred(function (k) {
                            f[0][3].add(a(0, k, bs(h) ? h : bY, k.notifyWith)), f[1][3].add(a(0, k, bs(i) ? i : bY)), f[2][3].add(a(0, k, bs(e) ? e : bu))
                        }).promise()
                    }, promise: function (a) {
                        return null != a ? b4.extend(a, b) : b
                    }
                }, g = {};
            return b4.each(f, function (a, j) {
                var h = j[2], i = j[5];
                b[j[1]] = h.add, i && h.add(function () {
                    d = i
                }, f[3 - a][2].disable, f[3 - a][3].disable, f[0][2].lock, f[0][3].lock), h.add(j[3].fire), g[j[0]] = function () {
                    return g[j[0] + "With"](this === g ? void 0 : this, arguments), this
                }, g[j[0] + "With"] = h.fireWith
            }), b.promise(g), c && c.call(g, g), g
        }, when: function (c) {
            var f = arguments.length, j = f, h = Array(j), d = b3.call(arguments), g = b4.Deferred(), b = function (a) {
                return function (i) {
                    h[a] = this, d[a] = 1 < arguments.length ? b3.call(arguments) : i, --f || g.resolveWith(h, d)
                }
            };
            if (f <= 1 && (a6(c, g.done(b(j)).resolve, g.reject, !f), "pending" === g.state() || bs(d[j] && d[j].then))) {
                return g.then()
            }
            while (j--) {
                a6(d[j], b(j), g.reject)
            }
            return g.promise()
        }
    });
    var cu = /^(Eval|Internal|Range|Reference|Syntax|Type|URI)Error$/;
    b4.Deferred.exceptionHook = function (a, b) {
        aw.console && aw.console.warn && a && cu.test(a.name) && aw.console.warn("jQuery.Deferred exception: " + a.message, a.stack, b)
    }, b4.readyException = function (a) {
        aw.setTimeout(function () {
            throw a
        })
    };
    var aO = b4.Deferred();

    function ao() {
        aI.removeEventListener("DOMContentLoaded", ao), aw.removeEventListener("load", ao), b4.ready()
    }

    b4.fn.ready = function (a) {
        return aO.then(a)["catch"](function (b) {
            b4.readyException(b)
        }), this
    }, b4.extend({
        isReady: !1, readyWait: 1, ready: function (a) {
            (!0 === a ? --b4.readyWait : b4.isReady) || (b4.isReady = !0) !== a && 0 < --b4.readyWait || aO.resolveWith(aI, [b4])
        }
    }), b4.ready.then = aO.then, "complete" === aI.readyState || "loading" !== aI.readyState && !aI.documentElement.doScroll ? aw.setTimeout(b4.ready) : (aI.addEventListener("DOMContentLoaded", ao), aw.addEventListener("load", ao));
    var aa = function (c, m, g, j, d, h, b) {
        var k = 0, p = c.length, f = null == g;
        if ("object" === cs(g)) {
            for (k in d = !0, g) {
                aa(c, m, k, g[k], !0, h, b)
            }
        } else {
            if (void 0 !== j && (d = !0, bs(j) || (b = !0), f && (b ? (m.call(c, j), m = null) : (f = m, m = function (a, l, i) {
                return f.call(b4(a), i)
            })), m)) {
                for (; k < p; k++) {
                    m(c[k], g, b ? j : j.call(c[k], k, m(c[k], g)))
                }
            }
        }
        return d ? c : f ? m.call(c) : p ? m(c[0], g) : h
    }, ad = /^-ms-/, cL = /-([a-z])/g;

    function ch(a, b) {
        return b.toUpperCase()
    }

    function cA(a) {
        return a.replace(ad, "ms-").replace(cL, ch)
    }

    var cn = function (a) {
        return 1 === a.nodeType || 9 === a.nodeType || !+a.nodeType
    };

    function aU() {
        this.expando = b4.expando + aU.uid++
    }

    aU.uid = 1, aU.prototype = {
        cache: function (a) {
            var b = a[this.expando];
            return b || (b = {}, cn(a) && (a.nodeType ? a[this.expando] = b : Object.defineProperty(a, this.expando, {
                value: b,
                configurable: !0
            }))), b
        }, set: function (a, f, c) {
            var d, b = this.cache(a);
            if ("string" == typeof f) {
                b[cA(f)] = c
            } else {
                for (d in f) {
                    b[cA(d)] = f[d]
                }
            }
            return b
        }, get: function (a, b) {
            return void 0 === b ? this.cache(a) : a[this.expando] && a[this.expando][cA(b)]
        }, access: function (a, c, b) {
            return void 0 === c || c && "string" == typeof c && void 0 === b ? this.get(a, c) : (this.set(a, c, b), void 0 !== b ? b : c)
        }, remove: function (a, d) {
            var b, c = a[this.expando];
            if (void 0 !== c) {
                if (void 0 !== d) {
                    b = (d = Array.isArray(d) ? d.map(cA) : (d = cA(d)) in c ? [d] : d.match(bM) || []).length;
                    while (b--) {
                        delete c[d[b]]
                    }
                }
                (void 0 === d || b4.isEmptyObject(c)) && (a.nodeType ? a[this.expando] = void 0 : delete a[this.expando])
            }
        }, hasData: function (a) {
            var b = a[this.expando];
            return void 0 !== b && !b4.isEmptyObject(b)
        }
    };
    var cG = new aU, bS = new aU, bc = /^(?:\{[\w\W]*\}|\[[\w\W]*\])$/, bi = /[A-Z]/g;

    function cM(a, f, c) {
        var d, b;
        if (void 0 === c && 1 === a.nodeType) {
            if (d = "data-" + f.replace(bi, "-$&").toLowerCase(), "string" == typeof (c = a.getAttribute(d))) {
                try {
                    c = "true" === (b = c) || "false" !== b && ("null" === b ? null : b === +b + "" ? +b : bc.test(b) ? JSON.parse(b) : b)
                } catch (a) {
                }
                bS.set(a, f, c)
            } else {
                c = void 0
            }
        }
        return c
    }

    b4.extend({
        hasData: function (a) {
            return bS.hasData(a) || cG.hasData(a)
        }, data: function (a, c, b) {
            return bS.access(a, c, b)
        }, removeData: function (a, b) {
            bS.remove(a, b)
        }, _data: function (a, c, b) {
            return cG.access(a, c, b)
        }, _removeData: function (a, b) {
            cG.remove(a, b)
        }
    }), b4.fn.extend({
        data: function (f, c) {
            var j, h, d, g = this[0], b = g && g.attributes;
            if (void 0 === f) {
                if (this.length && (d = bS.get(g), 1 === g.nodeType && !cG.get(g, "hasDataAttrs"))) {
                    j = b.length;
                    while (j--) {
                        b[j] && 0 === (h = b[j].name).indexOf("data-") && (h = cA(h.slice(5)), cM(g, h, d[h]))
                    }
                    cG.set(g, "hasDataAttrs", !0)
                }
                return d
            }
            return "object" == typeof f ? this.each(function () {
                bS.set(this, f)
            }) : aa(this, function (a) {
                var i;
                if (g && void 0 === a) {
                    return void 0 !== (i = bS.get(g, f)) ? i : void 0 !== (i = cM(g, f)) ? i : void 0
                }
                this.each(function () {
                    bS.set(this, f, a)
                })
            }, null, c, 1 < arguments.length, null, !0)
        }, removeData: function (a) {
            return this.each(function () {
                bS.remove(this, a)
            })
        }
    }), b4.extend({
        queue: function (a, d, b) {
            var c;
            if (a) {
                return d = (d || "fx") + "queue", c = cG.get(a, d), b && (!c || Array.isArray(b) ? c = cG.access(a, d, b4.makeArray(b)) : c.push(b)), c || []
            }
        }, dequeue: function (a, g) {
            g = g || "fx";
            var c = b4.queue(a, g), f = c.length, b = c.shift(), d = b4._queueHooks(a, g);
            "inprogress" === b && (b = c.shift(), f--), b && ("fx" === g && c.unshift("inprogress"), delete d.stop, b.call(a, function () {
                b4.dequeue(a, g)
            }, d)), !f && d && d.empty.fire()
        }, _queueHooks: function (a, c) {
            var b = c + "queueHooks";
            return cG.get(a, b) || cG.access(a, b, {
                empty: b4.Callbacks("once memory").add(function () {
                    cG.remove(a, [c + "queue", b])
                })
            })
        }
    }), b4.fn.extend({
        queue: function (c, b) {
            var a = 2;
            return "string" != typeof c && (b = c, c = "fx", a--), arguments.length < a ? b4.queue(this[0], c) : void 0 === b ? this : this.each(function () {
                var d = b4.queue(this, c, b);
                b4._queueHooks(this, c), "fx" === c && "inprogress" !== d[0] && b4.dequeue(this, c)
            })
        }, dequeue: function (a) {
            return this.each(function () {
                b4.dequeue(this, a)
            })
        }, clearQueue: function (a) {
            return this.queue(a || "fx", [])
        }, promise: function (c, k) {
            var f, h = 1, d = b4.Deferred(), g = this, b = this.length, j = function () {
                --h || d.resolveWith(g, [g])
            };
            "string" != typeof c && (k = c, c = void 0), c = c || "fx";
            while (b--) {
                (f = cG.get(g[b], c + "queueHooks")) && f.empty && (h++, f.empty.add(j))
            }
            return j(), d.promise(k)
        }
    });
    var aJ = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source, cb = new RegExp("^(?:([+-])=|)(" + aJ + ")([a-z%]*)$", "i"),
        bB = ["Top", "Right", "Bottom", "Left"], bZ = aI.documentElement, a7 = function (a) {
            return b4.contains(a.ownerDocument, a)
        }, bH = {composed: !0};
    bZ.getRootNode && (a7 = function (a) {
        return b4.contains(a.ownerDocument, a) || a.getRootNode(bH) === a.ownerDocument
    });
    var aj = function (a, b) {
        return "none" === (a = b || a).style.display || "" === a.style.display && a7(a) && "none" === b4.css(a, "display")
    };

    function b5(f, q, j, m) {
        var g, k, b = 20, p = m ? function () {
                return m.cur()
            } : function () {
                return b4.css(f, q, "")
            }, v = p(), h = j && j[3] || (b4.cssNumber[q] ? "" : "px"),
            d = f.nodeType && (b4.cssNumber[q] || "px" !== h && +v) && cb.exec(b4.css(f, q));
        if (d && d[3] !== h) {
            v /= 2, h = h || d[3], d = +v || 1;
            while (b--) {
                b4.style(f, q, d + h), (1 - k) * (1 - (k = p() / v || 0.5)) <= 0 && (b = 0), d /= k
            }
            d *= 2, b4.style(f, q, d + h), j = j || []
        }
        return j && (d = +d || +v || 0, g = j[1] ? d + (j[1] + 1) * j[2] : +j[2], m && (m.unit = h, m.start = d, m.end = g)), g
    }

    var ci = {};

    function bo(g, w) {
        for (var m, q, j, p, b, v, x, k = [], d = 0, h = g.length; d < h; d++) {
            (q = g[d]).style && (m = q.style.display, w ? ("none" === m && (k[d] = cG.get(q, "display") || null, k[d] || (q.style.display = "")), "" === q.style.display && aj(q) && (k[d] = (x = b = p = void 0, b = (j = q).ownerDocument, v = j.nodeName, (x = ci[v]) || (p = b.body.appendChild(b.createElement(v)), x = b4.css(p, "display"), p.parentNode.removeChild(p), "none" === x && (x = "block"), ci[v] = x)))) : "none" !== m && (k[d] = "none", cG.set(q, "display", m)))
        }
        for (d = 0; d < h; d++) {
            null != k[d] && (g[d].style.display = k[d])
        }
        return g
    }

    b4.fn.extend({
        show: function () {
            return bo(this, !0)
        }, hide: function () {
            return bo(this)
        }, toggle: function (a) {
            return "boolean" == typeof a ? a ? this.show() : this.hide() : this.each(function () {
                aj(this) ? b4(this).show() : b4(this).hide()
            })
        }
    });
    var ax, aP, bN = /^(?:checkbox|radio)$/i, aD = /<([a-z][^\/\0>\x20\t\r\n\f]*)/i,
        a1 = /^$|^module$|\/(?:java|ecma)script/i;
    ax = aI.createDocumentFragment().appendChild(aI.createElement("div")), (aP = aI.createElement("input")).setAttribute("type", "radio"), aP.setAttribute("checked", "checked"), aP.setAttribute("name", "t"), ax.appendChild(aP), cF.checkClone = ax.cloneNode(!0).cloneNode(!0).lastChild.checked, ax.innerHTML = "<textarea>x</textarea>", cF.noCloneChecked = !!ax.cloneNode(!0).lastChild.defaultValue, ax.innerHTML = "<option></option>", cF.option = !!ax.lastChild;
    var aV = {
        thead: [1, "<table>", "</table>"],
        col: [2, "<table><colgroup>", "</colgroup></table>"],
        tr: [2, "<table><tbody>", "</tbody></table>"],
        td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
        _default: [0, "", ""]
    };

    function co(a, c) {
        var b;
        return b = "undefined" != typeof a.getElementsByTagName ? a.getElementsByTagName(c || "*") : "undefined" != typeof a.querySelectorAll ? a.querySelectorAll(c || "*") : [], void 0 === c || c && ai(a, c) ? b4.merge([a], b) : b
    }

    function cH(a, d) {
        for (var b = 0, c = a.length; b < c; b++) {
            cG.set(a[b], "globalEval", !d || cG.get(d[b], "globalEval"))
        }
    }

    aV.tbody = aV.tfoot = aV.colgroup = aV.caption = aV.thead, aV.th = aV.td, cF.option || (aV.optgroup = aV.option = [1, "<select multiple='multiple'>", "</select>"]);
    var bv = /<|&#?\w+;/;

    function cB(k, C, x, A, v) {
        for (var y, b, B, D, w, g, m = C.createDocumentFragment(), z = [], j = 0, q = k.length; j < q; j++) {
            if ((y = k[j]) || 0 === y) {
                if ("object" === cs(y)) {
                    b4.merge(z, y.nodeType ? [y] : y)
                } else {
                    if (bv.test(y)) {
                        b = b || m.appendChild(C.createElement("div")), B = (aD.exec(y) || ["", ""])[1].toLowerCase(), D = aV[B] || aV._default, b.innerHTML = D[1] + b4.htmlPrefilter(y) + D[2], g = D[0];
                        while (g--) {
                            b = b.lastChild
                        }
                        b4.merge(z, b.childNodes), (b = m.firstChild).textContent = ""
                    } else {
                        z.push(C.createTextNode(y))
                    }
                }
            }
        }
        m.textContent = "", j = 0;
        while (y = z[j++]) {
            if (A && -1 < b4.inArray(y, A)) {
                v && v.push(y)
            } else {
                if (w = a7(y), b = co(m.appendChild(y), "script"), w && cH(b), x) {
                    g = 0;
                    while (y = b[g++]) {
                        a1.test(y.type || "") && x.push(y)
                    }
                }
            }
        }
        return m
    }

    var ap = /^key/, cv = /^(?:mouse|pointer|contextmenu|drag|drop)|click/, cc = /^([^.]*)(?:\.(.+)|)/;

    function ay() {
        return !0
    }

    function aK() {
        return !1
    }

    function b6(a, b) {
        return a === function () {
            try {
                return aI.activeElement
            } catch (c) {
            }
        }() == ("focus" === b)
    }

    function bj(c, k, f, h, d, g) {
        var b, j;
        if ("object" == typeof k) {
            for (j in "string" != typeof f && (h = h || f, f = void 0), k) {
                bj(c, j, f, h, k[j], g)
            }
            return c
        }
        if (null == h && null == d ? (d = f, h = f = void 0) : null == d && ("string" == typeof f ? (d = h, h = void 0) : (d = h, h = f, f = void 0)), !1 === d) {
            d = aK
        } else {
            if (!d) {
                return c
            }
        }
        return 1 === g && (b = d, (d = function (a) {
            return b4().off(a), b.apply(this, arguments)
        }).guid = b.guid || (b.guid = b4.guid++)), c.each(function () {
            b4.event.add(this, k, d, h, f)
        })
    }

    function ak(a, b, c) {
        c ? (cG.set(a, b, !1), b4.event.add(a, b, {
            namespace: !1, handler: function (d) {
                var h, f, g = cG.get(this, b);
                if (1 & d.isTrigger && this[b]) {
                    if (g.length) {
                        (b4.event.special[b] || {}).delegateType && d.stopPropagation()
                    } else {
                        if (g = b3.call(arguments), cG.set(this, b, g), h = c(this, b), this[b](), g !== (f = cG.get(this, b)) || h ? cG.set(this, b, !1) : f = {}, g !== f) {
                            return d.stopImmediatePropagation(), d.preventDefault(), f.value
                        }
                    }
                } else {
                    g.length && (cG.set(this, b, {value: b4.event.trigger(b4.extend(g[0], b4.Event.prototype), g.slice(1), this)}), d.stopImmediatePropagation())
                }
            }
        })) : void 0 === cG.get(a, b) && b4.event.add(a, b, ay)
    }

    b4.event = {
        global: {}, add: function (F, m, A, D, y) {
            var B, b, E, G, z, j, q, C, k, x, w, H = cG.get(F);
            if (cn(F)) {
                A.handler && (A = (B = A).handler, y = B.selector), y && b4.find.matchesSelector(bZ, y), A.guid || (A.guid = b4.guid++), (G = H.events) || (G = H.events = Object.create(null)), (b = H.handle) || (b = H.handle = function (a) {
                    return "undefined" != typeof b4 && b4.event.triggered !== a.type ? b4.event.dispatch.apply(F, arguments) : void 0
                }), z = (m = (m || "").match(bM) || [""]).length;
                while (z--) {
                    k = w = (E = cc.exec(m[z]) || [])[1], x = (E[2] || "").split(".").sort(), k && (q = b4.event.special[k] || {}, k = (y ? q.delegateType : q.bindType) || k, q = b4.event.special[k] || {}, j = b4.extend({
                        type: k,
                        origType: w,
                        data: D,
                        handler: A,
                        guid: A.guid,
                        selector: y,
                        needsContext: y && b4.expr.match.needsContext.test(y),
                        namespace: x.join(".")
                    }, B), (C = G[k]) || ((C = G[k] = []).delegateCount = 0, q.setup && !1 !== q.setup.call(F, D, x, b) || F.addEventListener && F.addEventListener(k, b)), q.add && (q.add.call(F, j), j.handler.guid || (j.handler.guid = A.guid)), y ? C.splice(C.delegateCount++, 0, j) : C.push(j), b4.event.global[k] = !0)
                }
            }
        }, remove: function (m, F, A, D, y) {
            var B, b, E, G, z, j, q, C, k, x, w, H = cG.hasData(m) && cG.get(m);
            if (H && (G = H.events)) {
                z = (F = (F || "").match(bM) || [""]).length;
                while (z--) {
                    if (k = w = (E = cc.exec(F[z]) || [])[1], x = (E[2] || "").split(".").sort(), k) {
                        q = b4.event.special[k] || {}, C = G[k = (D ? q.delegateType : q.bindType) || k] || [], E = E[2] && new RegExp("(^|\\.)" + x.join("\\.(?:.*\\.|)") + "(\\.|$)"), b = B = C.length;
                        while (B--) {
                            j = C[B], !y && w !== j.origType || A && A.guid !== j.guid || E && !E.test(j.namespace) || D && D !== j.selector && ("**" !== D || !j.selector) || (C.splice(B, 1), j.selector && C.delegateCount--, q.remove && q.remove.call(m, j))
                        }
                        b && !C.length && (q.teardown && !1 !== q.teardown.call(m, x, H.handle) || b4.removeEvent(m, k, H.handle), delete G[k])
                    } else {
                        for (k in G) {
                            b4.event.remove(m, k + F[z], A, D, !0)
                        }
                    }
                }
                b4.isEmptyObject(G) && cG.remove(m, "handle events")
            }
        }, dispatch: function (f) {
            var q, j, m, g, k, b, p = new Array(arguments.length), v = b4.event.fix(f),
                h = (cG.get(this, "events") || Object.create(null))[v.type] || [], d = b4.event.special[v.type] || {};
            for (p[0] = v, q = 1; q < arguments.length; q++) {
                p[q] = arguments[q]
            }
            if (v.delegateTarget = this, !d.preDispatch || !1 !== d.preDispatch.call(this, v)) {
                b = b4.event.handlers.call(this, v, h), q = 0;
                while ((g = b[q++]) && !v.isPropagationStopped()) {
                    v.currentTarget = g.elem, j = 0;
                    while ((k = g.handlers[j++]) && !v.isImmediatePropagationStopped()) {
                        v.rnamespace && !1 !== k.namespace && !v.rnamespace.test(k.namespace) || (v.handleObj = k, v.data = k.data, void 0 !== (m = ((b4.event.special[k.origType] || {}).handle || k.handler).apply(g.elem, p)) && !1 === (v.result = m) && (v.preventDefault(), v.stopPropagation()))
                    }
                }
                return d.postDispatch && d.postDispatch.call(this, v), v.result
            }
        }, handlers: function (c, m) {
            var g, j, d, h, b, k = [], p = m.delegateCount, f = c.target;
            if (p && f.nodeType && !("click" === c.type && 1 <= c.button)) {
                for (; f !== this; f = f.parentNode || this) {
                    if (1 === f.nodeType && ("click" !== c.type || !0 !== f.disabled)) {
                        for (h = [], b = {}, g = 0; g < p; g++) {
                            void 0 === b[d = (j = m[g]).selector + " "] && (b[d] = j.needsContext ? -1 < b4(d, this).index(f) : b4.find(d, this, null, [f]).length), b[d] && h.push(j)
                        }
                        h.length && k.push({elem: f, handlers: h})
                    }
                }
            }
            return f = this, p < m.length && k.push({elem: f, handlers: m.slice(p)}), k
        }, addProp: function (b, a) {
            Object.defineProperty(b4.Event.prototype, b, {
                enumerable: !0, configurable: !0, get: bs(a) ? function () {
                    if (this.originalEvent) {
                        return a(this.originalEvent)
                    }
                } : function () {
                    if (this.originalEvent) {
                        return this.originalEvent[b]
                    }
                }, set: function (c) {
                    Object.defineProperty(this, b, {enumerable: !0, configurable: !0, writable: !0, value: c})
                }
            })
        }, fix: function (a) {
            return a[b4.expando] ? a : new b4.Event(a)
        }, special: {
            load: {noBubble: !0}, click: {
                setup: function (a) {
                    var b = this || a;
                    return bN.test(b.type) && b.click && ai(b, "input") && ak(b, "click", ay), !1
                }, trigger: function (a) {
                    var b = this || a;
                    return bN.test(b.type) && b.click && ai(b, "input") && ak(b, "click"), !0
                }, _default: function (a) {
                    var b = a.target;
                    return bN.test(b.type) && b.click && ai(b, "input") && cG.get(b, "click") || ai(b, "a")
                }
            }, beforeunload: {
                postDispatch: function (a) {
                    void 0 !== a.result && a.originalEvent && (a.originalEvent.returnValue = a.result)
                }
            }
        }
    }, b4.removeEvent = function (a, c, b) {
        a.removeEventListener && a.removeEventListener(c, b)
    }, b4.Event = function (a, b) {
        if (!(this instanceof b4.Event)) {
            return new b4.Event(a, b)
        }
        a && a.type ? (this.originalEvent = a, this.type = a.type, this.isDefaultPrevented = a.defaultPrevented || void 0 === a.defaultPrevented && !1 === a.returnValue ? ay : aK, this.target = a.target && 3 === a.target.nodeType ? a.target.parentNode : a.target, this.currentTarget = a.currentTarget, this.relatedTarget = a.relatedTarget) : this.type = a, b && b4.extend(this, b), this.timeStamp = a && a.timeStamp || Date.now(), this[b4.expando] = !0
    }, b4.Event.prototype = {
        constructor: b4.Event,
        isDefaultPrevented: aK,
        isPropagationStopped: aK,
        isImmediatePropagationStopped: aK,
        isSimulated: !1,
        preventDefault: function () {
            var a = this.originalEvent;
            this.isDefaultPrevented = ay, a && !this.isSimulated && a.preventDefault()
        },
        stopPropagation: function () {
            var a = this.originalEvent;
            this.isPropagationStopped = ay, a && !this.isSimulated && a.stopPropagation()
        },
        stopImmediatePropagation: function () {
            var a = this.originalEvent;
            this.isImmediatePropagationStopped = ay, a && !this.isSimulated && a.stopImmediatePropagation(), this.stopPropagation()
        }
    }, b4.each({
        altKey: !0,
        bubbles: !0,
        cancelable: !0,
        changedTouches: !0,
        ctrlKey: !0,
        detail: !0,
        eventPhase: !0,
        metaKey: !0,
        pageX: !0,
        pageY: !0,
        shiftKey: !0,
        view: !0,
        "char": !0,
        code: !0,
        charCode: !0,
        key: !0,
        keyCode: !0,
        button: !0,
        buttons: !0,
        clientX: !0,
        clientY: !0,
        offsetX: !0,
        offsetY: !0,
        pointerId: !0,
        pointerType: !0,
        screenX: !0,
        screenY: !0,
        targetTouches: !0,
        toElement: !0,
        touches: !0,
        which: function (a) {
            var b = a.button;
            return null == a.which && ap.test(a.type) ? null != a.charCode ? a.charCode : a.keyCode : !a.which && void 0 !== b && cv.test(a.type) ? 1 & b ? 1 : 2 & b ? 3 : 4 & b ? 2 : 0 : a.which
        }
    }, b4.event.addProp), b4.each({focus: "focusin", blur: "focusout"}, function (a, b) {
        b4.event.special[a] = {
            setup: function () {
                return ak(this, a, b6), !1
            }, trigger: function () {
                return ak(this, a), !0
            }, delegateType: b
        }
    }), b4.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout",
        pointerenter: "pointerover",
        pointerleave: "pointerout"
    }, function (a, b) {
        b4.event.special[a] = {
            delegateType: b, bindType: b, handle: function (c) {
                var g, d = c.relatedTarget, f = c.handleObj;
                return d && (d === this || b4.contains(this, d)) || (c.type = f.origType, g = f.handler.apply(this, arguments), c.type = b), g
            }
        }
    }), b4.fn.extend({
        on: function (a, d, b, c) {
            return bj(this, a, d, b, c)
        }, one: function (a, d, b, c) {
            return bj(this, a, d, b, c, 1)
        }, off: function (a, f, c) {
            var d, b;
            if (a && a.preventDefault && a.handleObj) {
                return d = a.handleObj, b4(a.delegateTarget).off(d.namespace ? d.origType + "." + d.namespace : d.origType, d.selector, d.handler), this
            }
            if ("object" == typeof a) {
                for (b in a) {
                    this.off(b, f, a[b])
                }
                return this
            }
            return !1 !== f && "function" != typeof f || (c = f, f = void 0), !1 === c && (c = aK), this.each(function () {
                b4.event.remove(this, a, c, f)
            })
        }
    });
    var bC = /<script|<style|<link/i, aE = /checked\s*(?:[^=]|=\s*.checked.)/i,
        bd = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g;

    function bT(a, b) {
        return ai(a, "table") && ai(11 !== b.nodeType ? b : b.firstChild, "tr") && b4(a).children("tbody")[0] || a
    }

    function bp(a) {
        return a.type = (null !== a.getAttribute("type")) + "/" + a.type, a
    }

    function a2(a) {
        return "true/" === (a.type || "").slice(0, 5) ? a.type = a.type.slice(5) : a.removeAttribute("type"), a
    }

    function bI(c, k) {
        var f, h, d, g, b, j;
        if (1 === k.nodeType) {
            if (cG.hasData(c) && (j = cG.get(c).events)) {
                for (d in cG.remove(k, "handle events"), j) {
                    for (f = 0, h = j[d].length; f < h; f++) {
                        b4.event.add(k, d, j[d][f])
                    }
                }
            }
            bS.hasData(c) && (g = bS.access(c), b = b4.extend({}, g), bS.set(k, b))
        }
    }

    function bO(x, A, v, y) {
        A = aT(A);
        var k, C, b, B, D, w, g = 0, m = x.length, z = m - 1, j = A[0], q = bs(j);
        if (q || 1 < m && "string" == typeof j && !cF.checkClone && aE.test(j)) {
            return x.each(function (a) {
                var c = x.eq(a);
                q && (A[0] = j.call(this, a, c.html())), bO(c, A, v, y)
            })
        }
        if (m && (C = (k = cB(A, x[0].ownerDocument, !1, x, y)).firstChild, 1 === k.childNodes.length && (k = C), C || y)) {
            for (B = (b = b4.map(co(k, "script"), bp)).length; g < m; g++) {
                D = k, g !== z && (D = b4.clone(D, !0, !0), B && b4.merge(b, co(D, "script"))), v.call(x[g], D, g)
            }
            if (B) {
                for (w = b[b.length - 1].ownerDocument, b4.map(b, a2), g = 0; g < B; g++) {
                    D = b[g], a1.test(D.type || "") && !cG.access(D, "globalEval") && b4.contains(w, D) && (D.src && "module" !== (D.type || "").toLowerCase() ? b4._evalUrl && !D.noModule && b4._evalUrl(D.src, {nonce: D.nonce || D.getAttribute("nonce")}, w) : an(D.textContent.replace(bd, ""), D, w))
                }
            }
        }
        return x
    }

    function b0(a, g, c) {
        for (var f, b = g ? b4.filter(g, a) : a, d = 0; null != (f = b[d]); d++) {
            c || 1 !== f.nodeType || b4.cleanData(co(f)), f.parentNode && (c && a7(f) && cH(co(f, "script")), f.parentNode.removeChild(f))
        }
        return a
    }

    b4.extend({
        htmlPrefilter: function (a) {
            return a
        }, clone: function (g, w, m) {
            var q, j, p, b, v, x, k, d = g.cloneNode(!0), h = a7(g);
            if (!(cF.noCloneChecked || 1 !== g.nodeType && 11 !== g.nodeType || b4.isXMLDoc(g))) {
                for (b = co(d), q = 0, j = (p = co(g)).length; q < j; q++) {
                    v = p[q], x = b[q], void 0, "input" === (k = x.nodeName.toLowerCase()) && bN.test(v.type) ? x.checked = v.checked : "input" !== k && "textarea" !== k || (x.defaultValue = v.defaultValue)
                }
            }
            if (w) {
                if (m) {
                    for (p = p || co(g), b = b || co(d), q = 0, j = p.length; q < j; q++) {
                        bI(p[q], b[q])
                    }
                } else {
                    bI(g, d)
                }
            }
            return 0 < (b = co(d, "script")).length && cH(b, !h && co(g, "script")), d
        }, cleanData: function (a) {
            for (var g, c, f, b = b4.event.special, d = 0; void 0 !== (c = a[d]); d++) {
                if (cn(c)) {
                    if (g = c[cG.expando]) {
                        if (g.events) {
                            for (f in g.events) {
                                b[f] ? b4.event.remove(c, f) : b4.removeEvent(c, f, g.handle)
                            }
                        }
                        c[cG.expando] = void 0
                    }
                    c[bS.expando] && (c[bS.expando] = void 0)
                }
            }
        }
    }), b4.fn.extend({
        detach: function (a) {
            return b0(this, a, !0)
        }, remove: function (a) {
            return b0(this, a)
        }, text: function (a) {
            return aa(this, function (b) {
                return void 0 === b ? b4.text(this) : this.empty().each(function () {
                    1 !== this.nodeType && 11 !== this.nodeType && 9 !== this.nodeType || (this.textContent = b)
                })
            }, null, a, arguments.length)
        }, append: function () {
            return bO(this, arguments, function (a) {
                1 !== this.nodeType && 11 !== this.nodeType && 9 !== this.nodeType || bT(this, a).appendChild(a)
            })
        }, prepend: function () {
            return bO(this, arguments, function (a) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var b = bT(this, a);
                    b.insertBefore(a, b.firstChild)
                }
            })
        }, before: function () {
            return bO(this, arguments, function (a) {
                this.parentNode && this.parentNode.insertBefore(a, this)
            })
        }, after: function () {
            return bO(this, arguments, function (a) {
                this.parentNode && this.parentNode.insertBefore(a, this.nextSibling)
            })
        }, empty: function () {
            for (var a, b = 0; null != (a = this[b]); b++) {
                1 === a.nodeType && (b4.cleanData(co(a, !1)), a.textContent = "")
            }
            return this
        }, clone: function (a, b) {
            return a = null != a && a, b = null == b ? a : b, this.map(function () {
                return b4.clone(this, a, b)
            })
        }, html: function (a) {
            return aa(this, function (b) {
                var f = this[0] || {}, c = 0, d = this.length;
                if (void 0 === b && 1 === f.nodeType) {
                    return f.innerHTML
                }
                if ("string" == typeof b && !bC.test(b) && !aV[(aD.exec(b) || ["", ""])[1].toLowerCase()]) {
                    b = b4.htmlPrefilter(b);
                    try {
                        for (; c < d; c++) {
                            1 === (f = this[c] || {}).nodeType && (b4.cleanData(co(f, !1)), f.innerHTML = b)
                        }
                        f = 0
                    } catch (b) {
                    }
                }
                f && this.empty().append(b)
            }, null, a, arguments.length)
        }, replaceWith: function () {
            var a = [];
            return bO(this, arguments, function (b) {
                var c = this.parentNode;
                b4.inArray(this, a) < 0 && (b4.cleanData(co(this)), c && c.replaceChild(b, this))
            }, a)
        }
    }), b4.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    }, function (c, b) {
        b4.fn[c] = function (a) {
            for (var j, f = [], h = b4(a), d = h.length - 1, g = 0; g <= d; g++) {
                j = g === d ? this : this.clone(!0), b4(h[g])[b](j), cg.apply(f, j.get())
            }
            return this.pushStack(f)
        }
    });
    var bw = new RegExp("^(" + aJ + ")(?!px)[a-z%]+$", "i"), a8 = function (a) {
        var b = a.ownerDocument.defaultView;
        return b && b.opener || (b = aw), b.getComputedStyle(a)
    }, cw = function (a, g, c) {
        var f, b, d = {};
        for (b in g) {
            d[b] = a.style[b], a.style[b] = g[b]
        }
        for (b in f = c.call(a), g) {
            a.style[b] = d[b]
        }
        return f
    }, aQ = new RegExp(bB.join("|"), "i");

    function aq(c, k, f) {
        var h, d, g, b, j = c.style;
        return (f = f || a8(c)) && ("" !== (b = f.getPropertyValue(k) || f[k]) || a7(c) || (b = b4.style(c, k)), !cF.pixelBoxStyles() && bw.test(b) && aQ.test(k) && (h = j.width, d = j.minWidth, g = j.maxWidth, j.minWidth = j.maxWidth = j.width = b, b = f.width, j.width = h, j.minWidth = d, j.maxWidth = g)), void 0 !== b ? b + "" : b
    }

    function ab(a, b) {
        return {
            get: function () {
                if (!a()) {
                    return (this.get = b).apply(this, arguments)
                }
                delete this.get
            }
        }
    }

    !function () {
        function c() {
            if (f) {
                p.style.cssText = "position:absolute;left:-11111px;width:60px;margin-top:1px;padding:0;border:0", f.style.cssText = "position:relative;display:block;box-sizing:border-box;overflow:scroll;margin:auto;border:1px;padding:1px;width:60%;top:1%", bZ.appendChild(p).appendChild(f);
                var a = aw.getComputedStyle(f);
                g = "1%" !== a.top, k = 12 === m(a.marginLeft), f.style.right = "60%", h = 36 === m(a.right), j = 36 === m(a.width), f.style.position = "absolute", d = 12 === m(f.offsetWidth / 3), bZ.removeChild(p), f = null
            }
        }

        function m(a) {
            return Math.round(parseFloat(a))
        }

        var g, j, d, h, b, k, p = aI.createElement("div"), f = aI.createElement("div");
        f.style && (f.style.backgroundClip = "content-box", f.cloneNode(!0).style.backgroundClip = "", cF.clearCloneStyle = "content-box" === f.style.backgroundClip, b4.extend(cF, {
            boxSizingReliable: function () {
                return c(), j
            }, pixelBoxStyles: function () {
                return c(), h
            }, pixelPosition: function () {
                return c(), g
            }, reliableMarginLeft: function () {
                return c(), k
            }, scrollboxSize: function () {
                return c(), d
            }, reliableTrDimensions: function () {
                var a, o, i, l;
                return null == b && (a = aI.createElement("table"), o = aI.createElement("tr"), i = aI.createElement("div"), a.style.cssText = "position:absolute;left:-11111px", o.style.height = "1px", i.style.height = "9px", bZ.appendChild(a).appendChild(o).appendChild(i), l = aw.getComputedStyle(o), b = 3 < parseInt(l.height), bZ.removeChild(a)), b
            }
        }))
    }();
    var af = ["Webkit", "Moz", "ms"], cN = aI.createElement("div").style, cj = {};

    function cC(a) {
        var b = b4.cssProps[a] || cj[a];
        return b || (a in cN ? a : cj[a] = function (c) {
            var f = c[0].toUpperCase() + c.slice(1), d = af.length;
            while (d--) {
                if ((c = af[d] + f) in cN) {
                    return c
                }
            }
        }(a) || a)
    }

    var cp = /^(none|table(?!-c[ea]).+)/, aW = /^--/,
        cI = {position: "absolute", visibility: "hidden", display: "block"},
        bU = {letterSpacing: "0", fontWeight: "400"};

    function bf(a, d, b) {
        var c = cb.exec(d);
        return c ? Math.max(0, c[2] - (b || 0)) + (c[3] || "px") : d
    }

    function bk(c, k, f, h, d, g) {
        var b = "width" === k ? 1 : 0, j = 0, l = 0;
        if (f === (h ? "border" : "content")) {
            return 0
        }
        for (; b < 4; b += 2) {
            "margin" === f && (l += b4.css(c, f + bB[b], !0, d)), h ? ("content" === f && (l -= b4.css(c, "padding" + bB[b], !0, d)), "margin" !== f && (l -= b4.css(c, "border" + bB[b] + "Width", !0, d))) : (l += b4.css(c, "padding" + bB[b], !0, d), "padding" !== f ? l += b4.css(c, "border" + bB[b] + "Width", !0, d) : j += b4.css(c, "border" + bB[b] + "Width", !0, d))
        }
        return !h && 0 <= g && (l += Math.max(0, Math.ceil(c["offset" + k[0].toUpperCase() + k.slice(1)] - g - l - j - 0.5)) || 0), l
    }

    function cO(c, k, f) {
        var h = a8(c), d = (!cF.boxSizingReliable() || f) && "border-box" === b4.css(c, "boxSizing", !1, h), g = d,
            b = aq(c, k, h), j = "offset" + k[0].toUpperCase() + k.slice(1);
        if (bw.test(b)) {
            if (!f) {
                return b
            }
            b = "auto"
        }
        return (!cF.boxSizingReliable() && d || !cF.reliableTrDimensions() && ai(c, "tr") || "auto" === b || !parseFloat(b) && "inline" === b4.css(c, "display", !1, h)) && c.getClientRects().length && (d = "border-box" === b4.css(c, "boxSizing", !1, h), (g = j in c) && (b = c[j])), (b = parseFloat(b) || 0) + bk(c, k, f || (d ? "border" : "content"), g, h, b) + "px"
    }

    function aL(a, f, c, d, b) {
        return new aL.prototype.init(a, f, c, d, b)
    }

    b4.extend({
        cssHooks: {
            opacity: {
                get: function (a, c) {
                    if (c) {
                        var b = aq(a, "opacity");
                        return "" === b ? "1" : b
                    }
                }
            }
        },
        cssNumber: {
            animationIterationCount: !0,
            columnCount: !0,
            fillOpacity: !0,
            flexGrow: !0,
            flexShrink: !0,
            fontWeight: !0,
            gridArea: !0,
            gridColumn: !0,
            gridColumnEnd: !0,
            gridColumnStart: !0,
            gridRow: !0,
            gridRowEnd: !0,
            gridRowStart: !0,
            lineHeight: !0,
            opacity: !0,
            order: !0,
            orphans: !0,
            widows: !0,
            zIndex: !0,
            zoom: !0
        },
        cssProps: {},
        style: function (c, m, g, j) {
            if (c && 3 !== c.nodeType && 8 !== c.nodeType && c.style) {
                var d, h, b, k = cA(m), p = aW.test(m), f = c.style;
                if (p || (m = cC(k)), b = b4.cssHooks[m] || b4.cssHooks[k], void 0 === g) {
                    return b && "get" in b && void 0 !== (d = b.get(c, !1, j)) ? d : f[m]
                }
                "string" === (h = typeof g) && (d = cb.exec(g)) && d[1] && (g = b5(c, m, d), h = "number"), null != g && g == g && ("number" !== h || p || (g += d && d[3] || (b4.cssNumber[k] ? "" : "px")), cF.clearCloneStyle || "" !== g || 0 !== m.indexOf("background") || (f[m] = "inherit"), b && "set" in b && void 0 === (g = b.set(c, g, j)) || (p ? f.setProperty(m, g) : f[m] = g))
            }
        },
        css: function (c, k, f, h) {
            var d, g, b, j = cA(k);
            return aW.test(k) || (k = cC(j)), (b = b4.cssHooks[k] || b4.cssHooks[j]) && "get" in b && (d = b.get(c, !0, f)), void 0 === d && (d = aq(c, k, h)), "normal" === d && k in bU && (d = bU[k]), "" === f || f ? (g = parseFloat(d), !0 === f || isFinite(g) ? g || 0 : d) : d
        }
    }), b4.each(["height", "width"], function (a, b) {
        b4.cssHooks[b] = {
            get: function (c, f, d) {
                if (f) {
                    return !cp.test(b4.css(c, "display")) || c.getClientRects().length && c.getBoundingClientRect().width ? cO(c, b, d) : cw(c, cI, function () {
                        return cO(c, b, d)
                    })
                }
            }, set: function (d, l, g) {
                var j, f = a8(d), h = !cF.scrollboxSize() && "absolute" === f.position,
                    c = (h || g) && "border-box" === b4.css(d, "boxSizing", !1, f), k = g ? bk(d, b, g, c, f) : 0;
                return c && h && (k -= Math.ceil(d["offset" + b[0].toUpperCase() + b.slice(1)] - parseFloat(f[b]) - bk(d, b, "border", !1, f) - 0.5)), k && (j = cb.exec(l)) && "px" !== (j[3] || "px") && (d.style[b] = l, l = b4.css(d, b)), bf(0, l, k)
            }
        }
    }), b4.cssHooks.marginLeft = ab(cF.reliableMarginLeft, function (a, b) {
        if (b) {
            return (parseFloat(aq(a, "marginLeft")) || a.getBoundingClientRect().left - cw(a, {marginLeft: 0}, function () {
                return a.getBoundingClientRect().left
            })) + "px"
        }
    }), b4.each({margin: "", padding: "", border: "Width"}, function (a, b) {
        b4.cssHooks[a + b] = {
            expand: function (c) {
                for (var g = 0, d = {}, f = "string" == typeof c ? c.split(" ") : [c]; g < 4; g++) {
                    d[a + bB[g] + b] = f[g] || f[g - 2] || f[0]
                }
                return d
            }
        }, "margin" !== a && (b4.cssHooks[a + b].set = bf)
    }), b4.fn.extend({
        css: function (a, b) {
            return aa(this, function (d, k, g) {
                var j, f, h = {}, c = 0;
                if (Array.isArray(k)) {
                    for (j = a8(d), f = k.length; c < f; c++) {
                        h[k[c]] = b4.css(d, k[c], !1, j)
                    }
                    return h
                }
                return void 0 !== g ? b4.style(d, k, g) : b4.css(d, k)
            }, a, b, 1 < arguments.length)
        }
    }), ((b4.Tween = aL).prototype = {
        constructor: aL, init: function (a, g, c, f, b, d) {
            this.elem = a, this.prop = c, this.easing = b || b4.easing._default, this.options = g, this.start = this.now = this.cur(), this.end = f, this.unit = d || (b4.cssNumber[c] ? "" : "px")
        }, cur: function () {
            var a = aL.propHooks[this.prop];
            return a && a.get ? a.get(this) : aL.propHooks._default.get(this)
        }, run: function (a) {
            var c, b = aL.propHooks[this.prop];
            return this.options.duration ? this.pos = c = b4.easing[this.easing](a, this.options.duration * a, 0, 1, this.options.duration) : this.pos = c = a, this.now = (this.end - this.start) * c + this.start, this.options.step && this.options.step.call(this.elem, this.now, this), b && b.set ? b.set(this) : aL.propHooks._default.set(this), this
        }
    }).init.prototype = aL.prototype, (aL.propHooks = {
        _default: {
            get: function (a) {
                var b;
                return 1 !== a.elem.nodeType || null != a.elem[a.prop] && null == a.elem.style[a.prop] ? a.elem[a.prop] : (b = b4.css(a.elem, a.prop, "")) && "auto" !== b ? b : 0
            }, set: function (a) {
                b4.fx.step[a.prop] ? b4.fx.step[a.prop](a) : 1 !== a.elem.nodeType || !b4.cssHooks[a.prop] && null == a.elem.style[cC(a.prop)] ? a.elem[a.prop] = a.now : b4.style(a.elem, a.prop, a.now + a.unit)
            }
        }
    }).scrollTop = aL.propHooks.scrollLeft = {
        set: function (a) {
            a.elem.nodeType && a.elem.parentNode && (a.elem[a.prop] = a.now)
        }
    }, b4.easing = {
        linear: function (a) {
            return a
        }, swing: function (a) {
            return 0.5 - Math.cos(a * Math.PI) / 2
        }, _default: "swing"
    }, b4.fx = aL.prototype.init, b4.fx.step = {};
    var cd, bD, b1, a9, bJ = /^(?:toggle|show|hide)$/, al = /queueHooks$/;

    function b7() {
        bD && (!1 === aI.hidden && aw.requestAnimationFrame ? aw.requestAnimationFrame(b7) : aw.setTimeout(b7, b4.fx.interval), b4.fx.tick())
    }

    function ck() {
        return aw.setTimeout(function () {
            cd = void 0
        }), cd = Date.now()
    }

    function bq(a, f) {
        var c, d = 0, b = {height: a};
        for (f = f ? 1 : 0; d < 4; d += 2 - f) {
            b["margin" + (c = bB[d])] = b["padding" + c] = a
        }
        return f && (b.opacity = b.width = a), b
    }

    function az(c, j, f) {
        for (var h, d = (aR.tweeners[j] || []).concat(aR.tweeners["*"]), g = 0, b = d.length; g < b; g++) {
            if (h = d[g].call(f, j, c)) {
                return h
            }
        }
    }

    function aR(k, f, q) {
        var j, b, m = 0, g = aR.prefilters.length, p = b4.Deferred().always(function () {
            delete v.elem
        }), v = function () {
            if (b) {
                return !1
            }
            for (var a = cd || ck(), s = Math.max(0, h.startTime + h.duration - a), l = 1 - (s / h.duration || 0), o = 0, c = h.tweens.length; o < c; o++) {
                h.tweens[o].run(l)
            }
            return p.notifyWith(k, [h, l, s]), l < 1 && c ? s : (c || p.notifyWith(k, [h, 1, 0]), p.resolveWith(k, [h]), !1)
        }, h = p.promise({
            elem: k,
            props: b4.extend({}, f),
            opts: b4.extend(!0, {specialEasing: {}, easing: b4.easing._default}, q),
            originalProperties: f,
            originalOptions: q,
            startTime: cd || ck(),
            duration: q.duration,
            tweens: [],
            createTween: function (a, i) {
                var c = b4.Tween(k, h.opts, a, i, h.opts.specialEasing[a] || h.opts.easing);
                return h.tweens.push(c), c
            },
            stop: function (a) {
                var i = 0, c = a ? h.tweens.length : 0;
                if (b) {
                    return this
                }
                for (b = !0; i < c; i++) {
                    h.tweens[i].run(1)
                }
                return a ? (p.notifyWith(k, [h, 1, 0]), p.resolveWith(k, [h, a])) : p.rejectWith(k, [h, a]), this
            }
        }), d = h.props;
        for (!function (l, y) {
            var u, x, s, w, c;
            for (u in l) {
                if (s = y[x = cA(u)], w = l[u], Array.isArray(w) && (s = w[1], w = l[u] = w[0]), u !== x && (l[x] = w, delete l[u]), (c = b4.cssHooks[x]) && "expand" in c) {
                    for (u in w = c.expand(w), delete l[x], w) {
                        u in l || (l[u] = w[u], y[u] = s)
                    }
                } else {
                    y[x] = s
                }
            }
        }(d, h.opts.specialEasing); m < g; m++) {
            if (j = aR.prefilters[m].call(h, k, d, h.opts)) {
                return bs(j.stop) && (b4._queueHooks(h.elem, h.opts.queue).stop = j.stop.bind(j)), j
            }
        }
        return b4.map(d, az, h), bs(h.opts.start) && h.opts.start.call(k, h), h.progress(h.opts.progress).done(h.opts.done, h.opts.complete).fail(h.opts.fail).always(h.opts.always), b4.fx.timer(b4.extend(v, {
            elem: k,
            anim: h,
            queue: h.opts.queue
        })), h
    }

    b4.Animation = b4.extend(aR, {
        tweeners: {
            "*": [function (a, c) {
                var b = this.createTween(a, c);
                return b5(b.elem, a, cb.exec(c), b), b
            }]
        }, tweener: function (a, f) {
            bs(a) ? (f = a, a = ["*"]) : a = a.match(bM);
            for (var c, d = 0, b = a.length; d < b; d++) {
                c = a[d], aR.tweeners[c] = aR.tweeners[c] || [], aR.tweeners[c].unshift(f)
            }
        }, prefilters: [function (m, F, A) {
            var D, y, B, b, E, G, z, j, q = "width" in F || "height" in F, C = this, k = {}, x = m.style,
                w = m.nodeType && aj(m), H = cG.get(m, "fxshow");
            for (D in A.queue || (null == (b = b4._queueHooks(m, "fx")).unqueued && (b.unqueued = 0, E = b.empty.fire, b.empty.fire = function () {
                b.unqueued || E()
            }), b.unqueued++, C.always(function () {
                C.always(function () {
                    b.unqueued--, b4.queue(m, "fx").length || b.empty.fire()
                })
            })), F) {
                if (y = F[D], bJ.test(y)) {
                    if (delete F[D], B = B || "toggle" === y, y === (w ? "hide" : "show")) {
                        if ("show" !== y || !H || void 0 === H[D]) {
                            continue
                        }
                        w = !0
                    }
                    k[D] = H && H[D] || b4.style(m, D)
                }
            }
            if ((G = !b4.isEmptyObject(F)) || !b4.isEmptyObject(k)) {
                for (D in q && 1 === m.nodeType && (A.overflow = [x.overflow, x.overflowX, x.overflowY], null == (z = H && H.display) && (z = cG.get(m, "display")), "none" === (j = b4.css(m, "display")) && (z ? j = z : (bo([m], !0), z = m.style.display || z, j = b4.css(m, "display"), bo([m]))), ("inline" === j || "inline-block" === j && null != z) && "none" === b4.css(m, "float") && (G || (C.done(function () {
                    x.display = z
                }), null == z && (j = x.display, z = "none" === j ? "" : j)), x.display = "inline-block")), A.overflow && (x.overflow = "hidden", C.always(function () {
                    x.overflow = A.overflow[0], x.overflowX = A.overflow[1], x.overflowY = A.overflow[2]
                })), G = !1, k) {
                    G || (H ? "hidden" in H && (w = H.hidden) : H = cG.access(m, "fxshow", {display: z}), B && (H.hidden = !w), w && bo([m], !0), C.done(function () {
                        for (D in w || bo([m]), cG.remove(m, "fxshow"), k) {
                            b4.style(m, D, k[D])
                        }
                    })), G = az(w ? H[D] : 0, D, C), D in H || (H[D] = G.start, w && (G.end = G.start, G.start = 0))
                }
            }
        }], prefilter: function (a, b) {
            b ? aR.prefilters.unshift(a) : aR.prefilters.push(a)
        }
    }), b4.speed = function (a, d, b) {
        var c = a && "object" == typeof a ? b4.extend({}, a) : {
            complete: b || !b && d || bs(a) && a,
            duration: a,
            easing: b && d || d && !bs(d) && d
        };
        return b4.fx.off ? c.duration = 0 : "number" != typeof c.duration && (c.duration in b4.fx.speeds ? c.duration = b4.fx.speeds[c.duration] : c.duration = b4.fx.speeds._default), null != c.queue && !0 !== c.queue || (c.queue = "fx"), c.old = c.complete, c.complete = function () {
            bs(c.old) && c.old.call(this), c.queue && b4.dequeue(this, c.queue)
        }, c
    }, b4.fn.extend({
        fadeTo: function (a, d, b, c) {
            return this.filter(aj).css("opacity", 0).show().end().animate({opacity: d}, a, b, c)
        }, animate: function (j, c, f, h) {
            var d = b4.isEmptyObject(j), g = b4.speed(c, f, h), b = function () {
                var a = aR(this, b4.extend({}, j), g);
                (d || cG.get(this, "finish")) && a.stop(!0)
            };
            return b.finish = b, d || !1 === g.queue ? this.each(b) : this.queue(g.queue, b)
        }, stop: function (d, c, f) {
            var b = function (a) {
                var g = a.stop;
                delete a.stop, g(f)
            };
            return "string" != typeof d && (f = c, c = d, d = void 0), c && this.queue(d || "fx", []), this.each(function () {
                var a = !0, i = null != d && d + "queueHooks", g = b4.timers, h = cG.get(this);
                if (i) {
                    h[i] && h[i].stop && b(h[i])
                } else {
                    for (i in h) {
                        h[i] && h[i].stop && al.test(i) && b(h[i])
                    }
                }
                for (i = g.length; i--;) {
                    g[i].elem !== this || null != d && g[i].queue !== d || (g[i].anim.stop(f), a = !1, g.splice(i, 1))
                }
                !a && f || b4.dequeue(this, d)
            })
        }, finish: function (b) {
            return !1 !== b && (b = b || "fx"), this.each(function () {
                var a, h = cG.get(this), d = h[b + "queue"], g = h[b + "queueHooks"], c = b4.timers,
                    f = d ? d.length : 0;
                for (h.finish = !0, b4.queue(this, b, []), g && g.stop && g.stop.call(this, !0), a = c.length; a--;) {
                    c[a].elem === this && c[a].queue === b && (c[a].anim.stop(!0), c.splice(a, 1))
                }
                for (a = 0; a < f; a++) {
                    d[a] && d[a].finish && d[a].finish.call(this)
                }
                delete h.finish
            })
        }
    }), b4.each(["toggle", "show", "hide"], function (a, c) {
        var b = b4.fn[c];
        b4.fn[c] = function (d, g, f) {
            return null == d || "boolean" == typeof d ? b.apply(this, arguments) : this.animate(bq(c, !0), d, g, f)
        }
    }), b4.each({
        slideDown: bq("show"),
        slideUp: bq("hide"),
        slideToggle: bq("toggle"),
        fadeIn: {opacity: "show"},
        fadeOut: {opacity: "hide"},
        fadeToggle: {opacity: "toggle"}
    }, function (a, b) {
        b4.fn[a] = function (c, f, d) {
            return this.animate(b, c, f, d)
        }
    }), b4.timers = [], b4.fx.tick = function () {
        var a, c = 0, b = b4.timers;
        for (cd = Date.now(); c < b.length; c++) {
            (a = b[c])() || b[c] !== a || b.splice(c--, 1)
        }
        b.length || b4.fx.stop(), cd = void 0
    }, b4.fx.timer = function (a) {
        b4.timers.push(a), b4.fx.start()
    }, b4.fx.interval = 13, b4.fx.start = function () {
        bD || (bD = !0, b7())
    }, b4.fx.stop = function () {
        bD = null
    }, b4.fx.speeds = {slow: 600, fast: 200, _default: 400}, b4.fn.delay = function (b, a) {
        return b = b4.fx && b4.fx.speeds[b] || b, a = a || "fx", this.queue(a, function (c, f) {
            var d = aw.setTimeout(c, b);
            f.stop = function () {
                aw.clearTimeout(d)
            }
        })
    }, b1 = aI.createElement("input"), a9 = aI.createElement("select").appendChild(aI.createElement("option")), b1.type = "checkbox", cF.checkOn = "" !== b1.value, cF.optSelected = a9.selected, (b1 = aI.createElement("input")).value = "t", b1.type = "radio", cF.radioValue = "t" === b1.value;
    var bP, aF = b4.expr.attrHandle;
    b4.fn.extend({
        attr: function (a, b) {
            return aa(this, b4.attr, a, b, 1 < arguments.length)
        }, removeAttr: function (a) {
            return this.each(function () {
                b4.removeAttr(this, a)
            })
        }
    }), b4.extend({
        attr: function (a, g, c) {
            var f, b, d = a.nodeType;
            if (3 !== d && 8 !== d && 2 !== d) {
                return "undefined" == typeof a.getAttribute ? b4.prop(a, g, c) : (1 === d && b4.isXMLDoc(a) || (b = b4.attrHooks[g.toLowerCase()] || (b4.expr.match.bool.test(g) ? bP : void 0)), void 0 !== c ? null === c ? void b4.removeAttr(a, g) : b && "set" in b && void 0 !== (f = b.set(a, c, g)) ? f : (a.setAttribute(g, c + ""), c) : b && "get" in b && null !== (f = b.get(a, g)) ? f : null == (f = b4.find.attr(a, g)) ? void 0 : f)
            }
        }, attrHooks: {
            type: {
                set: function (a, c) {
                    if (!cF.radioValue && "radio" === c && ai(a, "input")) {
                        var b = a.value;
                        return a.setAttribute("type", c), b && (a.value = b), c
                    }
                }
            }
        }, removeAttr: function (a, f) {
            var c, d = 0, b = f && f.match(bM);
            if (b && 1 === a.nodeType) {
                while (c = b[d++]) {
                    a.removeAttribute(c)
                }
            }
        }
    }), bP = {
        set: function (a, c, b) {
            return !1 === c ? b4.removeAttr(a, b) : a.setAttribute(b, b), b
        }
    }, b4.each(b4.expr.match.bool.source.match(/\w+/g), function (c, d) {
        var b = aF[d] || b4.find.attr;
        aF[d] = function (a, k, g) {
            var j, f, h = k.toLowerCase();
            return g || (f = aF[h], aF[h] = j, j = null != b(a, k, g) ? h : null, aF[h] = f), j
        }
    });
    var a3 = /^(?:input|select|textarea|button)$/i, aX = /^(?:a|area)$/i;

    function cq(a) {
        return (a.match(bM) || []).join(" ")
    }

    function cJ(a) {
        return a.getAttribute && a.getAttribute("class") || ""
    }

    function bx(a) {
        return Array.isArray(a) ? a : "string" == typeof a && a.match(bM) || []
    }

    b4.fn.extend({
        prop: function (a, b) {
            return aa(this, b4.prop, a, b, 1 < arguments.length)
        }, removeProp: function (a) {
            return this.each(function () {
                delete this[b4.propFix[a] || a]
            })
        }
    }), b4.extend({
        prop: function (a, g, c) {
            var f, b, d = a.nodeType;
            if (3 !== d && 8 !== d && 2 !== d) {
                return 1 === d && b4.isXMLDoc(a) || (g = b4.propFix[g] || g, b = b4.propHooks[g]), void 0 !== c ? b && "set" in b && void 0 !== (f = b.set(a, c, g)) ? f : a[g] = c : b && "get" in b && null !== (f = b.get(a, g)) ? f : a[g]
            }
        }, propHooks: {
            tabIndex: {
                get: function (a) {
                    var b = b4.find.attr(a, "tabindex");
                    return b ? parseInt(b, 10) : a3.test(a.nodeName) || aX.test(a.nodeName) && a.href ? 0 : -1
                }
            }
        }, propFix: {"for": "htmlFor", "class": "className"}
    }), cF.optSelected || (b4.propHooks.selected = {
        get: function (a) {
            var b = a.parentNode;
            return b && b.parentNode && b.parentNode.selectedIndex, null
        }, set: function (a) {
            var b = a.parentNode;
            b && (b.selectedIndex, b.parentNode && b.parentNode.selectedIndex)
        }
    }), b4.each(["tabIndex", "readOnly", "maxLength", "cellSpacing", "cellPadding", "rowSpan", "colSpan", "useMap", "frameBorder", "contentEditable"], function () {
        b4.propFix[this.toLowerCase()] = this
    }), b4.fn.extend({
        addClass: function (k) {
            var c, f, h, d, g, b, j, l = 0;
            if (bs(k)) {
                return this.each(function (a) {
                    b4(this).addClass(k.call(this, a, cJ(this)))
                })
            }
            if ((c = bx(k)).length) {
                while (f = this[l++]) {
                    if (d = cJ(f), h = 1 === f.nodeType && " " + cq(d) + " ") {
                        b = 0;
                        while (g = c[b++]) {
                            h.indexOf(" " + g + " ") < 0 && (h += g + " ")
                        }
                        d !== (j = cq(h)) && f.setAttribute("class", j)
                    }
                }
            }
            return this
        }, removeClass: function (k) {
            var c, f, h, d, g, b, j, l = 0;
            if (bs(k)) {
                return this.each(function (a) {
                    b4(this).removeClass(k.call(this, a, cJ(this)))
                })
            }
            if (!arguments.length) {
                return this.attr("class", "")
            }
            if ((c = bx(k)).length) {
                while (f = this[l++]) {
                    if (d = cJ(f), h = 1 === f.nodeType && " " + cq(d) + " ") {
                        b = 0;
                        while (g = c[b++]) {
                            while (-1 < h.indexOf(" " + g + " ")) {
                                h = h.replace(" " + g + " ", " ")
                            }
                        }
                        d !== (j = cq(h)) && f.setAttribute("class", j)
                    }
                }
            }
            return this
        }, toggleClass: function (c, e) {
            var d = typeof c, b = "string" === d || Array.isArray(c);
            return "boolean" == typeof e && b ? e ? this.addClass(c) : this.removeClass(c) : bs(c) ? this.each(function (a) {
                b4(this).toggleClass(c.call(this, a, cJ(this), e), e)
            }) : this.each(function () {
                var a, h, f, g;
                if (b) {
                    h = 0, f = b4(this), g = bx(c);
                    while (a = g[h++]) {
                        f.hasClass(a) ? f.removeClass(a) : f.addClass(a)
                    }
                } else {
                    void 0 !== c && "boolean" !== d || ((a = cJ(this)) && cG.set(this, "__className__", a), this.setAttribute && this.setAttribute("class", a || !1 === c ? "" : cG.get(this, "__className__") || ""))
                }
            })
        }, hasClass: function (a) {
            var d, b, c = 0;
            d = " " + a + " ";
            while (b = this[c++]) {
                if (1 === b.nodeType && -1 < (" " + cq(cJ(b)) + " ").indexOf(d)) {
                    return !0
                }
            }
            return !1
        }
    });
    var cD = /\r/g;
    b4.fn.extend({
        val: function (c) {
            var d, a, b, f = this[0];
            return arguments.length ? (b = bs(c), this.each(function (g) {
                var h;
                1 === this.nodeType && (null == (h = b ? c.call(this, g, b4(this).val()) : c) ? h = "" : "number" == typeof h ? h += "" : Array.isArray(h) && (h = b4.map(h, function (i) {
                    return null == i ? "" : i + ""
                })), (d = b4.valHooks[this.type] || b4.valHooks[this.nodeName.toLowerCase()]) && "set" in d && void 0 !== d.set(this, h, "value") || (this.value = h))
            })) : f ? (d = b4.valHooks[f.type] || b4.valHooks[f.nodeName.toLowerCase()]) && "get" in d && void 0 !== (a = d.get(f, "value")) ? a : "string" == typeof (a = f.value) ? a.replace(cD, "") : null == a ? "" : a : void 0
        }
    }), b4.extend({
        valHooks: {
            option: {
                get: function (a) {
                    var b = b4.find.attr(a, "value");
                    return null != b ? b : cq(b4.text(a))
                }
            }, select: {
                get: function (c) {
                    var k, f, h, d = c.options, g = c.selectedIndex, b = "select-one" === c.type, j = b ? null : [],
                        l = b ? g + 1 : d.length;
                    for (h = g < 0 ? l : b ? g : 0; h < l; h++) {
                        if (((f = d[h]).selected || h === g) && !f.disabled && (!f.parentNode.disabled || !ai(f.parentNode, "optgroup"))) {
                            if (k = b4(f).val(), b) {
                                return k
                            }
                            j.push(k)
                        }
                    }
                    return j
                }, set: function (c, j) {
                    var f, h, d = c.options, g = b4.makeArray(j), b = d.length;
                    while (b--) {
                        ((h = d[b]).selected = -1 < b4.inArray(b4.valHooks.option.get(h), g)) && (f = !0)
                    }
                    return f || (c.selectedIndex = -1), g
                }
            }
        }
    }), b4.each(["radio", "checkbox"], function () {
        b4.valHooks[this] = {
            set: function (a, b) {
                if (Array.isArray(b)) {
                    return a.checked = -1 < b4.inArray(b4(a).val(), b)
                }
            }
        }, cF.checkOn || (b4.valHooks[this].get = function (a) {
            return null === a.getAttribute("value") ? "on" : a.value
        })
    }), cF.focusin = "onfocusin" in aw;
    var ar = /^(?:focusinfocus|focusoutblur)$/, cx = function (a) {
        a.stopPropagation()
    };
    b4.extend(b4.event, {
        trigger: function (k, C, x, A) {
            var v, y, b, B, D, w, g, m, z = [x || aI], j = cm.call(k, "type") ? k.type : k,
                q = cm.call(k, "namespace") ? k.namespace.split(".") : [];
            if (y = m = b = x = x || aI, 3 !== x.nodeType && 8 !== x.nodeType && !ar.test(j + b4.event.triggered) && (-1 < j.indexOf(".") && (j = (q = j.split(".")).shift(), q.sort()), D = j.indexOf(":") < 0 && "on" + j, (k = k[b4.expando] ? k : new b4.Event(j, "object" == typeof k && k)).isTrigger = A ? 2 : 3, k.namespace = q.join("."), k.rnamespace = k.namespace ? new RegExp("(^|\\.)" + q.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, k.result = void 0, k.target || (k.target = x), C = null == C ? [k] : b4.makeArray(C, [k]), g = b4.event.special[j] || {}, A || !g.trigger || !1 !== g.trigger.apply(x, C))) {
                if (!A && !g.noBubble && !cz(x)) {
                    for (B = g.delegateType || j, ar.test(B + j) || (y = y.parentNode); y; y = y.parentNode) {
                        z.push(y), b = y
                    }
                    b === (x.ownerDocument || aI) && z.push(b.defaultView || b.parentWindow || aw)
                }
                v = 0;
                while ((y = z[v++]) && !k.isPropagationStopped()) {
                    m = y, k.type = 1 < v ? B : g.bindType || j, (w = (cG.get(y, "events") || Object.create(null))[k.type] && cG.get(y, "handle")) && w.apply(y, C), (w = D && y[D]) && w.apply && cn(y) && (k.result = w.apply(y, C), !1 === k.result && k.preventDefault())
                }
                return k.type = j, A || k.isDefaultPrevented() || g._default && !1 !== g._default.apply(z.pop(), C) || !cn(x) || D && bs(x[j]) && !cz(x) && ((b = x[D]) && (x[D] = null), b4.event.triggered = j, k.isPropagationStopped() && m.addEventListener(j, cx), x[j](), k.isPropagationStopped() && m.removeEventListener(j, cx), b4.event.triggered = void 0, b && (x[D] = b)), k.result
            }
        }, simulate: function (a, d, b) {
            var c = b4.extend(new b4.Event, b, {type: a, isSimulated: !0});
            b4.event.trigger(c, null, d)
        }
    }), b4.fn.extend({
        trigger: function (a, b) {
            return this.each(function () {
                b4.event.trigger(a, b, this)
            })
        }, triggerHandler: function (a, c) {
            var b = this[0];
            if (b) {
                return b4.event.trigger(a, c, b, !0)
            }
        }
    }), cF.focusin || b4.each({focus: "focusin", blur: "focusout"}, function (b, c) {
        var a = function (d) {
            b4.event.simulate(c, d.target, b4.event.fix(d))
        };
        b4.event.special[c] = {
            setup: function () {
                var d = this.ownerDocument || this.document || this, f = cG.access(d, c);
                f || d.addEventListener(b, a, !0), cG.access(d, c, (f || 0) + 1)
            }, teardown: function () {
                var d = this.ownerDocument || this.document || this, f = cG.access(d, c) - 1;
                f ? cG.access(d, c, f) : (d.removeEventListener(b, a, !0), cG.remove(d, c))
            }
        }
    });
    var cf = aw.location, aA = {guid: Date.now()}, aM = /\?/;
    b4.parseXML = function (a) {
        var b;
        if (!a || "string" != typeof a) {
            return null
        }
        try {
            b = (new aw.DOMParser).parseFromString(a, "text/xml")
        } catch (a) {
            b = void 0
        }
        return b && !b.getElementsByTagName("parsererror").length || b4.error("Invalid XML: " + a), b
    };
    var b8 = /\[\]$/, bl = /\r?\n/g, am = /^(?:submit|button|image|reset|file)$/i,
        bE = /^(?:input|select|textarea|keygen)/i;

    function aG(c, a, d, b) {
        var f;
        if (Array.isArray(a)) {
            b4.each(a, function (g, h) {
                d || b8.test(c) ? b(c, h) : aG(c + "[" + ("object" == typeof h && null != h ? g : "") + "]", h, d, b)
            })
        } else {
            if (d || "object" !== cs(a)) {
                b(c, a)
            } else {
                for (f in a) {
                    aG(c + "[" + f + "]", a[f], d, b)
                }
            }
        }
    }

    b4.param = function (a, f) {
        var c, d = [], b = function (g, i) {
            var h = bs(i) ? i() : i;
            d[d.length] = encodeURIComponent(g) + "=" + encodeURIComponent(null == h ? "" : h)
        };
        if (null == a) {
            return ""
        }
        if (Array.isArray(a) || a.jquery && !b4.isPlainObject(a)) {
            b4.each(a, function () {
                b(this.name, this.value)
            })
        } else {
            for (c in a) {
                aG(c, a[c], f, b)
            }
        }
        return d.join("&")
    }, b4.fn.extend({
        serialize: function () {
            return b4.param(this.serializeArray())
        }, serializeArray: function () {
            return this.map(function () {
                var a = b4.prop(this, "elements");
                return a ? b4.makeArray(a) : this
            }).filter(function () {
                var a = this.type;
                return this.name && !b4(this).is(":disabled") && bE.test(this.nodeName) && !am.test(a) && (this.checked || !bN.test(a))
            }).map(function (a, c) {
                var b = b4(this).val();
                return null == b ? null : Array.isArray(b) ? b4.map(b, function (d) {
                    return {name: c.name, value: d.replace(bl, "\r\n")}
                }) : {name: c.name, value: b.replace(bl, "\r\n")}
            }).get()
        }
    });
    var bg = /%20/g, bV = /#.*$/, br = /([?&])_=[^&]*/, a4 = /^(.*?):[ \t]*([^\r\n]*)$/gm, bK = /^(?:GET|HEAD)$/,
        bQ = /^\/\//, b2 = {}, by = {}, ba = "*/".concat("*"), cy = aI.createElement("a");

    function aS(a) {
        return function (b, g) {
            "string" != typeof b && (g = b, b = "*");
            var d, f = 0, c = b.toLowerCase().match(bM) || [];
            if (bs(g)) {
                while (d = c[f++]) {
                    "+" === d[0] ? (d = d.slice(1) || "*", (a[d] = a[d] || []).unshift(g)) : (a[d] = a[d] || []).push(g)
                }
            }
        }
    }

    function au(g, c, e, b) {
        var f = {}, h = g === by;

        function d(a) {
            var i;
            return f[a] = !0, b4.each(g[a] || [], function (j, l) {
                var k = l(c, e, b);
                return "string" != typeof k || h || f[k] ? h ? !(i = k) : void 0 : (c.dataTypes.unshift(k), d(k), !1)
            }), i
        }

        return d(c.dataTypes[0]) || !f["*"] && d("*")
    }

    function ac(a, f) {
        var c, d, b = b4.ajaxSettings.flatOptions || {};
        for (c in f) {
            void 0 !== f[c] && ((b[c] ? a : d || (d = {}))[c] = f[c])
        }
        return d && b4.extend(!0, a, d), a
    }

    cy.href = cf.href, b4.extend({
        active: 0,
        lastModified: {},
        etag: {},
        ajaxSettings: {
            url: cf.href,
            type: "GET",
            isLocal: /^(?:about|app|app-storage|.+-extension|file|res|widget):$/.test(cf.protocol),
            global: !0,
            processData: !0,
            async: !0,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            accepts: {
                "*": ba,
                text: "text/plain",
                html: "text/html",
                xml: "application/xml, text/xml",
                json: "application/json, text/javascript"
            },
            contents: {xml: /\bxml\b/, html: /\bhtml/, json: /\bjson\b/},
            responseFields: {xml: "responseXML", text: "responseText", json: "responseJSON"},
            converters: {"* text": String, "text html": !0, "text json": JSON.parse, "text xml": b4.parseXML},
            flatOptions: {url: !0, context: !0}
        },
        ajaxSetup: function (a, b) {
            return b ? ac(ac(a, b4.ajaxSettings), b) : ac(b4.ajaxSettings, a)
        },
        ajaxPrefilter: aS(b2),
        ajaxTransport: aS(by),
        ajax: function (A, M) {
            "object" == typeof A && (M = A, A = void 0), M = M || {};
            var q, B, J, H, z, K, D, C, E, I, P = b4.ajaxSetup({}, M), S = P.context || P,
                G = P.context && (S.nodeType || S.jquery) ? b4(S) : b4.event, R = b4.Deferred(),
                k = b4.Callbacks("once memory"), Q = P.statusCode || {}, j = {}, L = {}, O = "canceled", N = {
                    readyState: 0, getResponseHeader: function (a) {
                        var b;
                        if (D) {
                            if (!H) {
                                H = {};
                                while (b = a4.exec(J)) {
                                    H[b[1].toLowerCase() + " "] = (H[b[1].toLowerCase() + " "] || []).concat(b[2])
                                }
                            }
                            b = H[a.toLowerCase() + " "]
                        }
                        return null == b ? null : b.join(", ")
                    }, getAllResponseHeaders: function () {
                        return D ? J : null
                    }, setRequestHeader: function (a, b) {
                        return null == D && (a = L[a.toLowerCase()] = L[a.toLowerCase()] || a, j[a] = b), this
                    }, overrideMimeType: function (a) {
                        return null == D && (P.mimeType = a), this
                    }, statusCode: function (a) {
                        var b;
                        if (a) {
                            if (D) {
                                N.always(a[N.status])
                            } else {
                                for (b in a) {
                                    Q[b] = [Q[b], a[b]]
                                }
                            }
                        }
                        return this
                    }, abort: function (a) {
                        var b = a || O;
                        return q && q.abort(b), F(0, b), this
                    }
                };
            if (R.promise(N), P.url = ((A || P.url || cf.href) + "").replace(bQ, cf.protocol + "//"), P.type = M.method || M.type || P.method || P.type, P.dataTypes = (P.dataType || "*").toLowerCase().match(bM) || [""], null == P.crossDomain) {
                K = aI.createElement("a");
                try {
                    K.href = P.url, K.href = K.href, P.crossDomain = cy.protocol + "//" + cy.host != K.protocol + "//" + K.host
                } catch (A) {
                    P.crossDomain = !0
                }
            }
            if (P.data && P.processData && "string" != typeof P.data && (P.data = b4.param(P.data, P.traditional)), au(b2, P, M, N), D) {
                return N
            }
            for (E in (C = b4.event && P.global) && 0 == b4.active++ && b4.event.trigger("ajaxStart"), P.type = P.type.toUpperCase(), P.hasContent = !bK.test(P.type), B = P.url.replace(bV, ""), P.hasContent ? P.data && P.processData && 0 === (P.contentType || "").indexOf("application/x-www-form-urlencoded") && (P.data = P.data.replace(bg, "+")) : (I = P.url.slice(B.length), P.data && (P.processData || "string" == typeof P.data) && (B += (aM.test(B) ? "&" : "?") + P.data, delete P.data), !1 === P.cache && (B = B.replace(br, "$1"), I = (aM.test(B) ? "&" : "?") + "_=" + aA.guid++ + I), P.url = B + I), P.ifModified && (b4.lastModified[B] && N.setRequestHeader("If-Modified-Since", b4.lastModified[B]), b4.etag[B] && N.setRequestHeader("If-None-Match", b4.etag[B])), (P.data && P.hasContent && !1 !== P.contentType || M.contentType) && N.setRequestHeader("Content-Type", P.contentType), N.setRequestHeader("Accept", P.dataTypes[0] && P.accepts[P.dataTypes[0]] ? P.accepts[P.dataTypes[0]] + ("*" !== P.dataTypes[0] ? ", " + ba + "; q=0.01" : "") : P.accepts["*"]), P.headers) {
                N.setRequestHeader(E, P.headers[E])
            }
            if (P.beforeSend && (!1 === P.beforeSend.call(S, N, P) || D)) {
                return N.abort()
            }
            if (O = "abort", k.add(P.complete), N.done(P.success), N.fail(P.error), q = au(by, P, M, N)) {
                if (N.readyState = 1, C && G.trigger("ajaxSend", [N, P]), D) {
                    return N
                }
                P.async && 0 < P.timeout && (z = aw.setTimeout(function () {
                    N.abort("timeout")
                }, P.timeout));
                try {
                    D = !1, q.send(j, F)
                } catch (A) {
                    if (D) {
                        throw A
                    }
                    F(-1, A)
                }
            } else {
                F(-1, "No Transport")
            }

            function F(c, v, g, m) {
                var d, h, b, p, w, f = v;
                D || (D = !0, z && aw.clearTimeout(z), q = void 0, J = m || "", N.readyState = 0 < c ? 4 : 0, d = 200 <= c && c < 300 || 304 === c, g && (p = function (x, X, T) {
                    var V, y, U, l, W = x.contents, Y = x.dataTypes;
                    while ("*" === Y[0]) {
                        Y.shift(), void 0 === V && (V = x.mimeType || X.getResponseHeader("Content-Type"))
                    }
                    if (V) {
                        for (y in W) {
                            if (W[y] && W[y].test(V)) {
                                Y.unshift(y);
                                break
                            }
                        }
                    }
                    if (Y[0] in T) {
                        U = Y[0]
                    } else {
                        for (y in T) {
                            if (!Y[0] || x.converters[y + " " + Y[0]]) {
                                U = y;
                                break
                            }
                            l || (l = y)
                        }
                        U = U || l
                    }
                    if (U) {
                        return U !== Y[0] && Y.unshift(U), T[U]
                    }
                }(P, N, g)), !d && -1 < b4.inArray("script", P.dataTypes) && (P.converters["text script"] = function () {
                }), p = function (T, ae, W, Y) {
                    var U, X, x, Z, at, V = {}, y = T.dataTypes.slice();
                    if (y[1]) {
                        for (x in T.converters) {
                            V[x.toLowerCase()] = T.converters[x]
                        }
                    }
                    X = y.shift();
                    while (X) {
                        if (T.responseFields[X] && (W[T.responseFields[X]] = ae), !at && Y && T.dataFilter && (ae = T.dataFilter(ae, T.dataType)), at = X, X = y.shift()) {
                            if ("*" === X) {
                                X = at
                            } else {
                                if ("*" !== at && at !== X) {
                                    if (!(x = V[at + " " + X] || V["* " + X])) {
                                        for (U in V) {
                                            if ((Z = U.split(" "))[1] === X && (x = V[at + " " + Z[0]] || V["* " + Z[0]])) {
                                                !0 === x ? x = V[U] : !0 !== V[U] && (X = Z[0], y.unshift(Z[1]));
                                                break
                                            }
                                        }
                                    }
                                    if (!0 !== x) {
                                        if (x && T["throws"]) {
                                            ae = x(ae)
                                        } else {
                                            try {
                                                ae = x(ae)
                                            } catch (T) {
                                                return {
                                                    state: "parsererror",
                                                    error: x ? T : "No conversion from " + at + " to " + X
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return {state: "success", data: ae}
                }(P, p, N, d), d ? (P.ifModified && ((w = N.getResponseHeader("Last-Modified")) && (b4.lastModified[B] = w), (w = N.getResponseHeader("etag")) && (b4.etag[B] = w)), 204 === c || "HEAD" === P.type ? f = "nocontent" : 304 === c ? f = "notmodified" : (f = p.state, h = p.data, d = !(b = p.error))) : (b = f, !c && f || (f = "error", c < 0 && (c = 0))), N.status = c, N.statusText = (v || f) + "", d ? R.resolveWith(S, [h, f, N]) : R.rejectWith(S, [N, f, b]), N.statusCode(Q), Q = void 0, C && G.trigger(d ? "ajaxSuccess" : "ajaxError", [N, P, d ? h : b]), k.fireWith(S, [N, f]), C && (G.trigger("ajaxComplete", [N, P]), --b4.active || b4.event.trigger("ajaxStop")))
            }

            return N
        },
        getJSON: function (a, c, b) {
            return b4.get(a, c, b, "json")
        },
        getScript: function (a, b) {
            return b4.get(a, void 0, b, "script")
        }
    }), b4.each(["get", "post"], function (a, b) {
        b4[b] = function (c, g, d, f) {
            return bs(g) && (f = f || d, d = g, g = void 0), b4.ajax(b4.extend({
                url: c,
                type: b,
                dataType: f,
                data: g,
                success: d
            }, b4.isPlainObject(c) && c))
        }
    }), b4.ajaxPrefilter(function (a) {
        var b;
        for (b in a.headers) {
            "content-type" === b.toLowerCase() && (a.contentType = a.headers[b] || "")
        }
    }), b4._evalUrl = function (a, c, b) {
        return b4.ajax({
            url: a,
            type: "GET",
            dataType: "script",
            cache: !0,
            async: !1,
            global: !1,
            converters: {
                "text script": function () {
                }
            },
            dataFilter: function (d) {
                b4.globalEval(d, c, b)
            }
        })
    }, b4.fn.extend({
        wrapAll: function (a) {
            var b;
            return this[0] && (bs(a) && (a = a.call(this[0])), b = b4(a, this[0].ownerDocument).eq(0).clone(!0), this[0].parentNode && b.insertBefore(this[0]), b.map(function () {
                var c = this;
                while (c.firstElementChild) {
                    c = c.firstElementChild
                }
                return c
            }).append(this)), this
        }, wrapInner: function (a) {
            return bs(a) ? this.each(function (b) {
                b4(this).wrapInner(a.call(this, b))
            }) : this.each(function () {
                var b = b4(this), c = b.contents();
                c.length ? c.wrapAll(a) : b.append(a)
            })
        }, wrap: function (b) {
            var a = bs(b);
            return this.each(function (c) {
                b4(this).wrapAll(a ? b.call(this, c) : b)
            })
        }, unwrap: function (a) {
            return this.parent(a).not("body").each(function () {
                b4(this).replaceWith(this.childNodes)
            }), this
        }
    }), b4.expr.pseudos.hidden = function (a) {
        return !b4.expr.pseudos.visible(a)
    }, b4.expr.pseudos.visible = function (a) {
        return !!(a.offsetWidth || a.offsetHeight || a.getClientRects().length)
    }, b4.ajaxSettings.xhr = function () {
        try {
            return new aw.XMLHttpRequest
        } catch (a) {
        }
    };
    var ag = {0: 200, 1223: 204}, cP = b4.ajaxSettings.xhr();
    cF.cors = !!cP && "withCredentials" in cP, cF.ajax = cP = !!cP, b4.ajaxTransport(function (c) {
        var d, b;
        if (cF.cors || cP && !c.crossDomain) {
            return {
                send: function (a, h) {
                    var f, g = c.xhr();
                    if (g.open(c.type, c.url, c.async, c.username, c.password), c.xhrFields) {
                        for (f in c.xhrFields) {
                            g[f] = c.xhrFields[f]
                        }
                    }
                    for (f in c.mimeType && g.overrideMimeType && g.overrideMimeType(c.mimeType), c.crossDomain || a["X-Requested-With"] || (a["X-Requested-With"] = "XMLHttpRequest"), a) {
                        g.setRequestHeader(f, a[f])
                    }
                    d = function (i) {
                        return function () {
                            d && (d = b = g.onload = g.onerror = g.onabort = g.ontimeout = g.onreadystatechange = null, "abort" === i ? g.abort() : "error" === i ? "number" != typeof g.status ? h(0, "error") : h(g.status, g.statusText) : h(ag[g.status] || g.status, g.statusText, "text" !== (g.responseType || "text") || "string" != typeof g.responseText ? {binary: g.response} : {text: g.responseText}, g.getAllResponseHeaders()))
                        }
                    }, g.onload = d(), b = g.onerror = g.ontimeout = d("error"), void 0 !== g.onabort ? g.onabort = b : g.onreadystatechange = function () {
                        4 === g.readyState && aw.setTimeout(function () {
                            d && b()
                        })
                    }, d = d("abort");
                    try {
                        g.send(c.hasContent && c.data || null)
                    } catch (a) {
                        if (d) {
                            throw a
                        }
                    }
                }, abort: function () {
                    d && d()
                }
            }
        }
    }), b4.ajaxPrefilter(function (a) {
        a.crossDomain && (a.contents.script = !1)
    }), b4.ajaxSetup({
        accepts: {script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"},
        contents: {script: /\b(?:java|ecma)script\b/},
        converters: {
            "text script": function (a) {
                return b4.globalEval(a), a
            }
        }
    }), b4.ajaxPrefilter("script", function (a) {
        void 0 === a.cache && (a.cache = !1), a.crossDomain && (a.type = "GET")
    }), b4.ajaxTransport("script", function (b) {
        var c, a;
        if (b.crossDomain || b.scriptAttrs) {
            return {
                send: function (d, f) {
                    c = b4("<script>").attr(b.scriptAttrs || {}).prop({
                        charset: b.scriptCharset,
                        src: b.url
                    }).on("load error", a = function (g) {
                        c.remove(), a = null, g && f("error" === g.type ? 404 : 200, g.type)
                    }), aI.head.appendChild(c[0])
                }, abort: function () {
                    a && a()
                }
            }
        }
    });
    var cl, cE = [], cr = /(=)\?(?=&|$)|\?\?/;
    b4.ajaxSetup({
        jsonp: "callback", jsonpCallback: function () {
            var a = cE.pop() || b4.expando + "_" + aA.guid++;
            return this[a] = !0, a
        }
    }), b4.ajaxPrefilter("json jsonp", function (c, j, f) {
        var h, d, g,
            b = !1 !== c.jsonp && (cr.test(c.url) ? "url" : "string" == typeof c.data && 0 === (c.contentType || "").indexOf("application/x-www-form-urlencoded") && cr.test(c.data) && "data");
        if (b || "jsonp" === c.dataTypes[0]) {
            return h = c.jsonpCallback = bs(c.jsonpCallback) ? c.jsonpCallback() : c.jsonpCallback, b ? c[b] = c[b].replace(cr, "$1" + h) : !1 !== c.jsonp && (c.url += (aM.test(c.url) ? "&" : "?") + c.jsonp + "=" + h), c.converters["script json"] = function () {
                return g || b4.error(h + " was not called"), g[0]
            }, c.dataTypes[0] = "json", d = aw[h], aw[h] = function () {
                g = arguments
            }, f.always(function () {
                void 0 === d ? b4(aw).removeProp(h) : aw[h] = d, c[h] && (c.jsonpCallback = j.jsonpCallback, cE.push(h)), g && bs(d) && d(g[0]), g = d = void 0
            }), "script"
        }
    }), cF.createHTMLDocument = ((cl = aI.implementation.createHTMLDocument("").body).innerHTML = "<form></form><form></form>", 2 === cl.childNodes.length), b4.parseHTML = function (a, g, c) {
        return "string" != typeof a ? [] : ("boolean" == typeof g && (c = g, g = !1), g || (cF.createHTMLDocument ? ((f = (g = aI.implementation.createHTMLDocument("")).createElement("base")).href = aI.location.href, g.head.appendChild(f)) : g = aI), d = !c && [], (b = bA.exec(a)) ? [g.createElement(b[1])] : (b = cB([a], g, d), d && d.length && b4(d).remove(), b4.merge([], b.childNodes)));
        var f, b, d
    }, b4.fn.load = function (c, k, f) {
        var h, d, g, b = this, j = c.indexOf(" ");
        return -1 < j && (h = cq(c.slice(j)), c = c.slice(0, j)), bs(k) ? (f = k, k = void 0) : k && "object" == typeof k && (d = "POST"), 0 < b.length && b4.ajax({
            url: c,
            type: d || "GET",
            dataType: "html",
            data: k
        }).done(function (a) {
            g = arguments, b.html(h ? b4("<div>").append(b4.parseHTML(a)).find(h) : a)
        }).always(f && function (a, i) {
            b.each(function () {
                f.apply(this, g || [a.responseText, i, a])
            })
        }), this
    }, b4.expr.pseudos.animated = function (a) {
        return b4.grep(b4.timers, function (b) {
            return a === b.elem
        }).length
    }, b4.offset = {
        setOffset: function (g, w, m) {
            var q, j, p, b, v, x, k = b4.css(g, "position"), d = b4(g), h = {};
            "static" === k && (g.style.position = "relative"), v = d.offset(), p = b4.css(g, "top"), x = b4.css(g, "left"), ("absolute" === k || "fixed" === k) && -1 < (p + x).indexOf("auto") ? (b = (q = d.position()).top, j = q.left) : (b = parseFloat(p) || 0, j = parseFloat(x) || 0), bs(w) && (w = w.call(g, m, b4.extend({}, v))), null != w.top && (h.top = w.top - v.top + b), null != w.left && (h.left = w.left - v.left + j), "using" in w ? w.using.call(g, h) : ("number" == typeof h.top && (h.top += "px"), "number" == typeof h.left && (h.left += "px"), d.css(h))
        }
    }, b4.fn.extend({
        offset: function (d) {
            if (arguments.length) {
                return void 0 === d ? this : this.each(function (f) {
                    b4.offset.setOffset(this, d, f)
                })
            }
            var a, b, c = this[0];
            return c ? c.getClientRects().length ? (a = c.getBoundingClientRect(), b = c.ownerDocument.defaultView, {
                top: a.top + b.pageYOffset,
                left: a.left + b.pageXOffset
            }) : {top: 0, left: 0} : void 0
        }, position: function () {
            if (this[0]) {
                var a, f, c, d = this[0], b = {top: 0, left: 0};
                if ("fixed" === b4.css(d, "position")) {
                    f = d.getBoundingClientRect()
                } else {
                    f = this.offset(), c = d.ownerDocument, a = d.offsetParent || c.documentElement;
                    while (a && (a === c.body || a === c.documentElement) && "static" === b4.css(a, "position")) {
                        a = a.parentNode
                    }
                    a && a !== d && 1 === a.nodeType && ((b = b4(a).offset()).top += b4.css(a, "borderTopWidth", !0), b.left += b4.css(a, "borderLeftWidth", !0))
                }
                return {
                    top: f.top - b.top - b4.css(d, "marginTop", !0),
                    left: f.left - b.left - b4.css(d, "marginLeft", !0)
                }
            }
        }, offsetParent: function () {
            return this.map(function () {
                var a = this.offsetParent;
                while (a && "static" === b4.css(a, "position")) {
                    a = a.offsetParent
                }
                return a || bZ
            })
        }
    }), b4.each({scrollLeft: "pageXOffset", scrollTop: "pageYOffset"}, function (c, a) {
        var b = "pageYOffset" === a;
        b4.fn[c] = function (d) {
            return aa(this, function (f, i, g) {
                var h;
                if (cz(f) ? h = f : 9 === f.nodeType && (h = f.defaultView), void 0 === g) {
                    return h ? h[a] : f[i]
                }
                h ? h.scrollTo(b ? h.pageXOffset : g, b ? g : h.pageYOffset) : f[i] = g
            }, c, d, arguments.length)
        }
    }), b4.each(["top", "left"], function (a, b) {
        b4.cssHooks[b] = ab(cF.pixelPosition, function (c, d) {
            if (d) {
                return d = aq(c, b), bw.test(d) ? b4(c).position()[b] + "px" : d
            }
        })
    }), b4.each({Height: "height", Width: "width"}, function (b, c) {
        b4.each({padding: "inner" + b, content: c, "": "outer" + b}, function (d, a) {
            b4.fn[a] = function (f, j) {
                var h = arguments.length && (d || "boolean" != typeof f),
                    g = d || (!0 === f || !0 === j ? "margin" : "border");
                return aa(this, function (i, m, k) {
                    var l;
                    return cz(i) ? 0 === a.indexOf("outer") ? i["inner" + b] : i.document.documentElement["client" + b] : 9 === i.nodeType ? (l = i.documentElement, Math.max(i.body["scroll" + b], l["scroll" + b], i.body["offset" + b], l["offset" + b], l["client" + b])) : void 0 === k ? b4.css(i, m, g) : b4.style(i, m, k, g)
                }, c, h ? f : void 0, h)
            }
        })
    }), b4.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"], function (a, b) {
        b4.fn[b] = function (c) {
            return this.on(b, c)
        }
    }), b4.fn.extend({
        bind: function (a, c, b) {
            return this.on(a, null, c, b)
        }, unbind: function (a, b) {
            return this.off(a, null, b)
        }, delegate: function (a, d, b, c) {
            return this.on(d, a, b, c)
        }, undelegate: function (a, c, b) {
            return 1 === arguments.length ? this.off(a, "**") : this.off(c, a || "**", b)
        }, hover: function (a, b) {
            return this.mouseenter(a).mouseleave(b || a)
        }
    }), b4.each("blur focus focusin focusout resize scroll click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup contextmenu".split(" "), function (a, b) {
        b4.fn[b] = function (c, d) {
            return 0 < arguments.length ? this.on(b, null, c, d) : this.trigger(b)
        }
    });
    var aY = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
    b4.proxy = function (a, f) {
        var c, d, b;
        if ("string" == typeof f && (c = a[f], f = a, a = c), bs(a)) {
            return d = b3.call(arguments, 2), (b = function () {
                return a.apply(f || this, d.concat(b3.call(arguments)))
            }).guid = a.guid = a.guid || b4.guid++, b
        }
    }, b4.holdReady = function (a) {
        a ? b4.readyWait++ : b4.ready(!0)
    }, b4.isArray = Array.isArray, b4.parseJSON = JSON.parse, b4.nodeName = ai, b4.isFunction = bs, b4.isWindow = cz, b4.camelCase = cA, b4.type = cs, b4.now = Date.now, b4.isNumeric = function (a) {
        var b = b4.type(a);
        return ("number" === b || "string" === b) && !isNaN(a - parseFloat(a))
    }, b4.trim = function (a) {
        return null == a ? "" : (a + "").replace(aY, "")
    }, "function" == typeof define && define.amd && define("jquery", [], function () {
        return b4
    });
    var cK = aw.jQuery, bW = aw.$;
    return b4.noConflict = function (a) {
        return aw.$ === b4 && (aw.$ = bW), a && aw.jQuery === b4 && (aw.jQuery = cK), b4
    }, "undefined" == typeof aH && (aw.jQuery = aw.$ = b4), b4
});