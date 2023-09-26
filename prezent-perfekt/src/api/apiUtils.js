export const extractLink = (data, propertyName) => {
    const link = (() => {
        try {
            return data._embedded[propertyName];
        }
        catch {
            return [];
        }
    })();
    console.log(link);
    return link;
}