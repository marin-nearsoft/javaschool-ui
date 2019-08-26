$(document).ready(function(){
    function _getCities(cities){
        _fillSelect(cities, $('#originSelect'));
        _fillSelect(cities, $('#destinationSelect'));
    }

    function _fillSelect(array, select){
        if(select.find('option').length <= 1){
            $(array).each(function(index, city){
                select.append(createOption(city));
            });
        }
    }

    function _changeCity(event){
        let originSelected = $('#originSelect option:selected').val();
        let destinationSelected = $('#destinationSelect option:selected').val();
        let isOriginValid = originSelected !== 'empty';
        let isDestinationValid = destinationSelected !== 'empty';
        let areOptionsValid = isOriginValid && isDestinationValid;
        if(areOptionsValid){
            rest('POST', config.rest.cityPath, _cityPath, {
                origin: originSelected,
                destination: destinationSelected
            });
        }
    }

    function _cityPath(path){
        $('#cityPath').val(path);
    }

    rest('GET', config.rest.city, _getCities);
    rest('GET', config.rest.size, _sizes);
    rest('GET', config.rest.shippingInformation, _fillShippingInformation)

    function _sizes(sizes){
        _fillSelect(sizes, $('#sizeSelect'));
    }

    $('.city-select').change(_changeCity);
    $('#sizeSelect').change(_fillTypeSelect);
    $('#typeSelect').change(_fillTimeSelect);
    $('#timeSelect').change(_fillTransportSelect);

    function _fillTypeSelect(){
        rest('GET', config.rest.type, _types);
    }

    function _types(sizes){
        _fillSelect(sizes, $('#typeSelect'));
    }

    function _fillTimeSelect(){
        rest('GET', config.rest.time, _times);
    }

    function _times(times){
        _fillSelect(times, $('#timeSelect'));
    }

    function _fillTransportSelect(){
        rest('GET', config.rest.transport, _transports);
    }

    function _transports(transports){
        _fillSelect(transports, $('#transportSelect'));
    }

    function _fillShippingInformation(shippingInformations){
        $(shippingInformations).each(function(index, shippingInformation){
            fillShippingTable(shippingInformation);
        });
    }
});