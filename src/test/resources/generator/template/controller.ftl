package ${basePackage}.web;
import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.core.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}.setCreateTime(new Date());
        ${modelNameLowerCamel}.setIsDelete(false);
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        Result result=ResultGenerator.genSuccessResult();
        result.setData(${modelNameLowerCamel});
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel}=new ${modelNameUpperCamel}();
        ${modelNameLowerCamel}.setId(id);
        ${modelNameLowerCamel}.setIsDelete(true);
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}.setUpdateTime(new Date());
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        Result result=ResultGenerator.genSuccessResult();
        result.setData(${modelNameLowerCamel});
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) ${modelNameUpperCamel} ${modelNameLowerCamel}) {
    PageHelper.startPage(page, size);
    ${modelNameLowerCamel}.setIsDelete(false);
    List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findByModel(${modelNameLowerCamel});
    PageInfo pageInfo = new PageInfo(list);
    return ResultGenerator.genSuccessResult(pageInfo);
}
