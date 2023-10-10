angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {

    if ($localStorage.springWebUser) {
        try {
            let jwt = $localStorage.springWebUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Data().getTime() / 1000);
            if (currentTime > payload.exp) {
                clearUser();
                console.log("Hi");
            }
        }
        catch (e) {

        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products/all',
            method: 'GET',
            params: {
                p: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.loadCart = function () {
        $http.get("http://localhost:5555/cart/api/v1/cart").then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get("http://localhost:5555/cart/api/v1/cart/add/" + productId).then(function (response) {
            $scope.loadCart();
        })
    }

    $scope.createOrder = function () {
        $http.post(contextPath + "/orders").then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart();
});